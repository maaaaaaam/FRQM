IF NOT EXISTS (SELECT name FROM sys.databases WHERE name = 'QuotesDb')
BEGIN
    CREATE DATABASE QuotesDb;
END
GO

USE QuotesDb;
GO

IF NOT EXISTS (SELECT * FROM sys.tables WHERE name='Quotes')
BEGIN
    CREATE TABLE Quotes (
        id INT PRIMARY KEY IDENTITY(1,1),
        text NVARCHAR(1000) NOT NULL,
        author NVARCHAR(100)
    );
END
GO

IF NOT EXISTS (SELECT 1 FROM Quotes)
BEGIN
    INSERT INTO Quotes (text, author) VALUES
    (N'Be yourself; everyone else is already taken.', N'Oscar Wilde'),
    (N'Two things are infinite: the universe and human stupidity; and I''m not sure about the universe.', N'Albert Einstein'),
    (N'So many books, so little time.', N'Frank Zappa'),
    (N'A room without books is like a body without a soul.', N'Marcus Tullius Cicero'),
    (N'Be the change that you wish to see in the world.', N'Mahatma Gandhi'),
    (N'In three words I can sum up everything I''ve learned about life: it goes on.', N'Robert Frost'),
    (N'If you tell the truth, you don''t have to remember anything.', N'Mark Twain'),
    (N'To live is the rarest thing in the world. Most people exist, that is all.', N'Oscar Wilde'),
    (N'We accept the love we think we deserve.', N'Stephen Chbosky'),
    (N'Without music, life would be a mistake.', N'Friedrich Nietzsche');
END
GO
