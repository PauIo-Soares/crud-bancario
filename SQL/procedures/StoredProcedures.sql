use db_sistema_bancario

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
				RAISERROR('CPF já existe!', 16, 1)
				RETURN
			END
			INSERT INTO tb_clientes VALUES(@cpf, GETDATE() ,@nome,@senha)
			SET @saida = @nome + ' foi inserido com sucesso!'
		END
		ELSE
		BEGIN
			RAISERROR('Senha inválida, é necessário ter 8 caracteres, sendo 3 numeros', 16, 1)
		END
	END
	GO


Create procedure sp_cria_conta(
@cpfcliente VARCHAR(11), @cpfconjunto VARCHAR(11), @nomeConjunto VARCHAR(100), @senhaConjunto VARCHAR(8), @agencia VARCHAR(10), @opcaoConta CHAR(1), @saida VARCHAR(200) OUTPUT)
AS 
	DECLARE @dataAniversario date, @codigo VARCHAR(20)
	IF(UPPER(@opcaoConta) LIKE 'C')
	BEGIN
		IF(@cpfconjunto IS NULL)
		BEGIN
			EXEC sp_cria_codigo_conta @cpfcliente, NULL, @agencia, @codigo OUTPUT
			INSERT INTO tb_contas_correntes VALUES (500.00, @codigo)
			INSERT INTO tb_contas VALUES (@codigo, GETDATE(), 0.0, @agencia)
			SET @saida = 'Conta corrente criada com sucesso!'
			Return
		END
		ELSE
		BEGIN
			EXEC sp_cria_cliente  @cpfconjunto, @nomeConjunto, @senhaConjunto, @saida OUTPUT
			EXEC sp_cria_codigo_conta @cpfcliente, @cpfconjunto, @agencia, @codigo OUTPUT
			INSERT INTO tb_contas_correntes VALUES (500.00, @codigo)
			INSERT INTO tb_contas VALUES (@codigo, GETDATE(), 0.0, @agencia)
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
				INSERT INTO tb_contas_poupancas VALUES (@dataAniversario, 0.01, @codigo)
				INSERT INTO tb_contas VALUES (@codigo, GETDATE(), 0.0, @agencia)
				SET @saida = 'Conta poupança criada com sucesso!'
				RETURN
			END
			ELSE
			BEGIN
				EXEC sp_cria_cliente  @cpfconjunto, @nomeConjunto, @senhaConjunto, @saida OUTPUT
				EXEC sp_cria_codigo_conta @cpfcliente, @cpfconjunto, @agencia, @codigo OUTPUT
				INSERT INTO tb_contas_poupancas VALUES (@dataAniversario, 0.01, @codigo)
				INSERT INTO tb_contas VALUES (@codigo, GETDATE(), 0.0, @agencia)
				SET @saida = @saida + ' Conta poupança conjunta criada com sucesso!'
				RETURN
			END
		END
	END
	







DECLARE @out1 VARCHAR(MAX)
EXEC sp_cria_cliente '11', 'Joao', '312dogui', @out1 OUTPUT
print(@out1)


DROP PROCEDURE sp_cria_cliente


select * from tb_clientes
select * from tb_agencias
Select * from tb_contas
Select * from tb_contas_correntes
Select * from tb_contas_poupancas
Select * from tb_titulares_conta

SELECT * FROM tb_agencias
SELECT * FROM tb_instituicoes_bancarias
