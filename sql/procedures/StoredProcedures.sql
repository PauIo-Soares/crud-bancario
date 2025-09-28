use db_sistema_bancario
go

Create procedure sp_valida_senha(
@senha VARCHAR(8), @valido BIT OUTPUT)
AS
	DECLARE @i INT = 1, @totalNum INT = 0, @char char(1)
	IF(len(@senha) < 8 OR len(@senha) > 8)
	BEGIN
		SET @valido = 0
		RETURN
	END
	WHILE @i <= len(@senha)
	BEGIN

		IF(SUBSTRING(@senha, @i, 1) LIKE '[0-9]')
		BEGIN
			SET @totalNum += 1
		END
		IF(@totalNum = 3)
		BEGIN
			SET @valido = 1
			RETURN
		END
		SET @i += 1
	END
	SET @valido = 0
	GO

Create procedure sp_cria_codigo_conta(
@cpfcliente VARCHAR(11), @cpfconjunto VARCHAR(11), @agencia VARCHAR(10), @codigo varchar(20) OUTPUT)
AS
	DECLARE @totalCPF1 INT = 0, @digito int = 0, @i INT = 1
	WHILE(@i <= len(@cpfcliente))
	BEGIN
		SET @totalCPF1 += CAST(SUBSTRING(@cpfcliente, @i, 1) AS INT)
		set @i += 1
	END
	IF(@cpfconjunto IS NOT NULL)
	BEGIN
		DECLARE @totalCPF2 INT = 0, @j INT = 1
		WHILE(@j <= len(@cpfconjunto))
		BEGIN
			SET @totalCPF2 += CAST(SUBSTRING(@cpfconjunto, @j, 1) AS INT)
			set @j += 1
		END
		SET @digito = (@totalCPF1 + @totalCPF2)%5
		SET @codigo = @agencia + SUBSTRING(@cpfcliente, 9, 3) + SUBSTRING(@cpfconjunto, 9, 3) + CAST(@digito as VARCHAR(1))
		RETURN
	END
	ELSE
	BEGIN
		SET @digito = @totalCPF1%5
		SET @codigo = @agencia + SUBSTRING(@cpfcliente, 9, 3) + CAST(@digito AS VARCHAR(1))
		RETURN
	END
	GO



Create procedure sp_cria_cliente(
@cpf VARCHAR(11), @nome VARCHAR(100), @senha VARCHAR(8), @saida varchar(200) OUTPUT)
AS
	DECLARE @erro		VARCHAR(MAX), @valido BIT
	IF(@cpf IS NULL or @nome IS NULL or @senha IS NULL)
	BEGIN
		RAISERROR('Preencha todas as informacoes!', 16, 1)
	END
	IF(len(@cpf) < 11)
	BEGIN
		RAISERROR('O CPF precisa ter 11 caracteres!', 16, 1)
	END
	ELSE
	BEGIN
		EXEC sp_valida_senha @senha, @valido OUTPUT
		IF(@valido = 1)
		BEGIN
			IF EXISTS (SELECT * FROM tb_clientes WHERE cpf = @cpf)
			BEGIN
				RAISERROR('CPF j� existe!', 16, 1)
				RETURN
			END
			INSERT INTO tb_clientes VALUES(@cpf, GETDATE() ,@nome,@senha)
			SET @saida = @nome + ' foi inserido com sucesso!'
		END
		ELSE
		BEGIN
			RAISERROR('Senha inv�lida, � necess�rio ter 8 caracteres, sendo 3 numeros', 16, 1)
		END
	END
	GO

	CREATE procedure sp_insere_titulares(
@cpfcliente varchar(11), @cpfconjunto varchar(11), @IDConta int, @saida VARCHAR(100) OUTPUT)
AS
	IF(@cpfconjunto IS NULL)
	BEGIN
		INSERT INTO tb_titulares_conta(cliente_id, conta_id) values (@cpfcliente, @IDConta)
		SET @saida = ' Inserido em titulares com sucesso!'
	END
	ELSE
	BEGIN
		INSERT INTO tb_titulares_conta(cliente_id, conta_id) values (@cpfcliente, @IDConta)
		INSERT INTO tb_titulares_conta(cliente_id, conta_id) values (@cpfconjunto, @IDConta)
		SET @saida = ' Inseridos em titulares com sucesso!'
	END
	GO


Create procedure sp_cria_conta(
@cpfcliente VARCHAR(11), @cpfconjunto VARCHAR(11), @nomeConjunto VARCHAR(100), @senhaConjunto VARCHAR(8), @agencia VARCHAR(10), @opcaoConta CHAR(1), @saida VARCHAR(200) OUTPUT)
AS
	DECLARE @dataAniversario date, @codigo VARCHAR(20), @maxID int
	IF NOT EXISTS (SELECT * FROM tb_clientes WHERE cpf LIKE @cpfcliente)
	BEGIN
		RAISERROR('O cliente n�o existe. � necess�rio criar um cliente antes!', 16, 1)
		RETURN
	END
	IF EXISTS (SELECT * FROM tb_clientes where cpf LIKE @cpfcliente)
	BEGIN
		exec sp_cria_codigo_conta @cpfcliente, NULL, @agencia, @codigo output
		IF EXISTS (SELECT * FROM tb_contas where codigo LIKE @codigo)
		BEGIN
			RAISERROR('A conta j� existe.', 16, 1)
			RETURN
		END
	END
	IF(UPPER(@opcaoConta) LIKE 'C')
	BEGIN
		IF(@cpfconjunto IS NULL)
		BEGIN
			EXEC sp_cria_codigo_conta @cpfcliente, NULL, @agencia, @codigo OUTPUT
			INSERT INTO tb_contas(id, codigo, data_abertura, saldo, agencia_id) VALUES (NEXT VALUE for instit_seq, @codigo, GETDATE(), 0.0, @agencia)
			Select @maxID = MAX(id) from tb_contas
			INSERT INTO tb_contas_correntes VALUES (500, @maxID)
			EXEC sp_insere_titulares @cpfcliente, NULL, @maxID, @saida OUTPUT
			SET @saida = @saida + ' Conta corrente criada com sucesso!'
			Return
		END
		ELSE
		BEGIN
			EXEC sp_cria_cliente  @cpfconjunto, @nomeConjunto, @senhaConjunto, @saida OUTPUT
			EXEC sp_cria_codigo_conta @cpfcliente, @cpfconjunto, @agencia, @codigo OUTPUT
			INSERT INTO tb_contas(id, codigo, data_abertura, saldo, agencia_id) VALUES (NEXT VALUE for instit_seq, @codigo, GETDATE(), 0.0, @agencia)
			Select @maxID = MAX(id) from tb_contas
			INSERT INTO tb_contas_correntes VALUES (500, @maxID)
			EXEC sp_insere_titulares @cpfcliente, @cpfconjunto, @maxID, @saida OUTPUT
			SET @saida = @saida + ' Conta corrente conjunta criada com sucesso!'
			RETURN
		END
	END
	ELSE
	BEGIN
		IF(UPPER(@opcaoConta) LIKE 'P')
		BEGIN
			SET @dataAniversario = DATEFROMPARTS(YEAR(GETDATE()), MONTH(GETDATE()), 10)
			IF(@cpfconjunto IS NULL)
			BEGIN
				EXEC sp_cria_codigo_conta @cpfcliente, NULL, @agencia, @codigo OUTPUT
				INSERT INTO tb_contas(id, codigo, data_abertura, saldo, agencia_id) VALUES (NEXT VALUE for instit_seq, @codigo, GETDATE(), 0.0, @agencia)
				Select @maxID = MAX(id) from tb_contas
				INSERT INTO tb_contas_poupancas VALUES (@dataAniversario, 0.01, @maxID)
				EXEC sp_insere_titulares @cpfcliente, NULL, @maxID, @saida OUTPUT
				SET @saida = @saida + ' Conta poupan�a criada com sucesso!'
				RETURN
			END
			ELSE
			BEGIN
				EXEC sp_cria_cliente  @cpfconjunto, @nomeConjunto, @senhaConjunto, @saida OUTPUT
				EXEC sp_cria_codigo_conta @cpfcliente, @cpfconjunto, @agencia, @codigo OUTPUT
				INSERT INTO tb_contas(id, codigo, data_abertura, saldo, agencia_id) VALUES (NEXT VALUE for instit_seq, @codigo, GETDATE(), 0.0, @agencia)
				Select @maxID = MAX(id) from tb_contas
				INSERT INTO tb_contas_poupancas VALUES (@dataAniversario, 0.01, @maxID)
				EXEC sp_insere_titulares @cpfcliente, @cpfconjunto, @maxID, @saida OUTPUT
				SET @saida = @saida + ' Conta poupan�a conjunta criada com sucesso!'
				RETURN
			END
		END
	END
	GO




CREATE PROCEDURE sp_insere_segundo_titular(
@cpfcliente varchar(11), @cpfconjunto varchar(11), @nome varchar(100), @senha varchar(100), @codigo varchar(20), @data_abertura date, @saida varchar(200) OUTPUT)
AS
	DECLARE @saldo decimal(10,2)
	SELECT @saldo = saldo from tb_contas where codigo = @codigo
	IF(@saldo = 0.00 AND @data_abertura <> GETDATE())
	Begin
		RAISERROR('O saldo precisa ser maior que 0', 16, 1)
		RETURN
	END
	ELSE
	BEGIN
		DECLARE @idConta int, @agencia varchar(10), @codConta varchar(20)
		SELECT @idConta = conta_id from tb_titulares_conta where cliente_id = @cpfcliente
		SELECT @agencia = agencia_id from tb_contas where id = @idConta
		exec sp_cria_cliente @cpfconjunto, @nome, @senha, @saida OUTPUT
		exec sp_cria_codigo_conta @cpfcliente, @cpfconjunto, @agencia, @codConta OUTPUT

		UPDATE tb_contas
		SET codigo = @codConta
		where id = @idConta

		exec sp_insere_titulares @cpfconjunto, NULL, @idConta, @saida OUTPUT

		set @saida = @saida + ' Segundo titular inclu�do com sucesso!'
	END
	GO


CREATE Procedure sp_atualiza_senha(
@cpfcliente varchar(11), @novaSenha varchar(30), @saida varchar(200) OUTPUT)
AS
	IF(@cpfcliente IS NULL or @novaSenha IS NULL)
	BEGIN
		RAISERROR('Todos os campos precisam estar preenchidos!',16,1)
		RETURN
	END
	UPDATE tb_clientes
	SET senha = @novaSenha
	where cpf = @cpfcliente
	SET @saida = 'Senha do cliente de cpf ' + @cpfcliente + ' atualizada!'
	GO


CREATE Procedure sp_atualiza_saldo(
@codigo varchar(20), @saldo decimal(10,2), @saida varchar(200) output)
AS
	DECLARE @idConta int
	SELECT @idConta = id from tb_contas where codigo = @codigo
	UPDATE tb_contas
	set saldo = @saldo
	where id = @idConta
	set @saida = 'Saldo da conta ' + cast(@idConta as varchar(20)) + ' atualizado com sucesso.'
	GO

CREATE Procedure sp_atualiza_limite(
@codigo varchar(20), @novoLimite decimal(10,2), @saida varchar(200) OUTPUT)
AS
	DECLARE @idConta int
	SELECT @idConta = id from tb_contas where codigo = @codigo
	UPDATE tb_contas_correntes
	SET limite_credito = @novoLimite
	where id = @idConta
	set @saida = 'Limite da conta ' + cast(@idConta as varchar(20)) + ' atualizado com sucesso.'
	GO

CREATE Procedure sp_atualiza_rendimento(
@codigo varchar(20), @novoRendimento decimal(3,3), @saida varchar(200) OUTPUT)
AS
	DECLARE @idConta int
	SELECT @idConta = id from tb_contas where codigo = @codigo
	UPDATE tb_contas_poupancas
	SET rendimento = @novoRendimento
	where id = @idConta
	set @saida = 'Rendimento da conta ' + cast(@idConta as varchar(20)) + ' atualizado com sucesso.'
	GO


--CREATE Procedure sp_deleta_cliente(
--@cpfcliente varchar(11), @codigo varchar(20), @saida varchar(200) output)
--AS
--	SET NOCOUNT ON
--	DECLARE @idConta int
--	SELECT @idConta = id from tb_contas where codigo = @codigo
--	IF(LEN(@codigo) > 7)
--	BEGIN
--		RAISERROR ('N�o � poss�vel excluir uma conta conjunta!', 16, 1)
--		RETURN
--	END

--	DELETE from tb_contas_correntes where id = @idConta
--	DELETE from tb_contas_poupancas where id = @idConta
--	DELETE from tb_titulares_conta where conta_id = @idConta
--	DELETE from tb_clientes where cpf = @cpfcliente
--	DELETE from tb_contas where id = @idConta

--	SET @saida = 'Cliente e contas associadas excluidas com sucesso!'
--	GO


CREATE OR ALTER PROCEDURE sp_deleta_cliente(
@cpfcliente VARCHAR(11),
@saida VARCHAR(200) OUTPUT)
AS
BEGIN
    SET NOCOUNT ON;

    DECLARE @idConta INT, @codigo VARCHAR(20);

    -- Buscar as contas do cliente
    DECLARE contas_cursor CURSOR FOR
        SELECT c.id, c.codigo
        FROM tb_contas c
        INNER JOIN tb_titulares_conta tc ON tc.conta_id = c.id
        WHERE tc.cliente_id = @cpfcliente;

    OPEN contas_cursor;
    FETCH NEXT FROM contas_cursor INTO @idConta, @codigo;

    WHILE @@FETCH_STATUS = 0
    BEGIN
        -- Verifica se é conta conjunta
        IF (LEN(@codigo) > 7)
        BEGIN
            SET @saida = 'Não é possível excluir cliente com conta conjunta!';
            CLOSE contas_cursor;
            DEALLOCATE contas_cursor;
            RETURN;
        END

        -- Excluir dependências
        DELETE FROM tb_contas_correntes WHERE id = @idConta;
        DELETE FROM tb_contas_poupancas WHERE id = @idConta;
        DELETE FROM tb_titulares_conta WHERE conta_id = @idConta;
        DELETE FROM tb_contas WHERE id = @idConta;

        FETCH NEXT FROM contas_cursor INTO @idConta, @codigo;
    END

    CLOSE contas_cursor;
    DEALLOCATE contas_cursor;

    -- Excluir cliente
    DELETE FROM tb_clientes WHERE cpf = @cpfcliente;

    SET @saida = 'Cliente e contas associadas excluídas com sucesso!';
END
GO