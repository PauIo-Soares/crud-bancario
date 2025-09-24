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

	
	
	
Create procedure sp_cria_cliente(
@cpf VARCHAR(11), @nome VARCHAR(100), @senha VARCHAR(8), @saida varchar(200) OUTPUT)
AS
	DECLARE @erro		VARCHAR(MAX), @valido BIT
	IF(@cpf IS NULL or @nome IS NULL or @senha IS NULL)
	BEGIN
		RAISERROR('Preencha todas as informacoes!', 16, 1)
	END
	ELSE
	BEGIN
		EXEC sp_valida_senha @senha, @valido OUTPUT
		IF(@valido = 1)
		BEGIN
			INSERT INTO tb_clientes VALUES(@cpf, GETDATE() ,@nome,@senha)
			SET @saida = @nome + ' foi inserido com sucesso!'
		END
		ELSE
		BEGIN
			RAISERROR('Senha inválida, é necessário ter 8 caracteres, sendo 3 numeros', 16, 1)
		END
	END


Declare @out1 VARCHAR(MAX)
EXEC sp_cria_cliente '38588813840', 'Joao', '123dogui', @out1 OUTPUT
print(@out1)

DROP PROCEDURE sp_valida_senha