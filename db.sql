USE [master]
GO
/****** Object:  Database [SmartReview]    Script Date: 7/21/2020 11:00:56 AM ******/
CREATE DATABASE [SmartReview]
 CONTAINMENT = NONE
 ON  PRIMARY 
( NAME = N'SmartReview', FILENAME = N'T:\ITs\SqlServer\ServerInstances\MSSQL12.TNT\MSSQL\DATA\SmartReview.mdf' , SIZE = 13312KB , MAXSIZE = UNLIMITED, FILEGROWTH = 1024KB )
 LOG ON 
( NAME = N'SmartReview_log', FILENAME = N'T:\ITs\SqlServer\ServerInstances\MSSQL12.TNT\MSSQL\DATA\SmartReview_log.ldf' , SIZE = 2816KB , MAXSIZE = 2048GB , FILEGROWTH = 10%)
GO
ALTER DATABASE [SmartReview] SET COMPATIBILITY_LEVEL = 120
GO
IF (1 = FULLTEXTSERVICEPROPERTY('IsFullTextInstalled'))
begin
EXEC [SmartReview].[dbo].[sp_fulltext_database] @action = 'enable'
end
GO
ALTER DATABASE [SmartReview] SET ANSI_NULL_DEFAULT OFF 
GO
ALTER DATABASE [SmartReview] SET ANSI_NULLS OFF 
GO
ALTER DATABASE [SmartReview] SET ANSI_PADDING OFF 
GO
ALTER DATABASE [SmartReview] SET ANSI_WARNINGS OFF 
GO
ALTER DATABASE [SmartReview] SET ARITHABORT OFF 
GO
ALTER DATABASE [SmartReview] SET AUTO_CLOSE OFF 
GO
ALTER DATABASE [SmartReview] SET AUTO_SHRINK OFF 
GO
ALTER DATABASE [SmartReview] SET AUTO_UPDATE_STATISTICS ON 
GO
ALTER DATABASE [SmartReview] SET CURSOR_CLOSE_ON_COMMIT OFF 
GO
ALTER DATABASE [SmartReview] SET CURSOR_DEFAULT  GLOBAL 
GO
ALTER DATABASE [SmartReview] SET CONCAT_NULL_YIELDS_NULL OFF 
GO
ALTER DATABASE [SmartReview] SET NUMERIC_ROUNDABORT OFF 
GO
ALTER DATABASE [SmartReview] SET QUOTED_IDENTIFIER OFF 
GO
ALTER DATABASE [SmartReview] SET RECURSIVE_TRIGGERS OFF 
GO
ALTER DATABASE [SmartReview] SET  DISABLE_BROKER 
GO
ALTER DATABASE [SmartReview] SET AUTO_UPDATE_STATISTICS_ASYNC OFF 
GO
ALTER DATABASE [SmartReview] SET DATE_CORRELATION_OPTIMIZATION OFF 
GO
ALTER DATABASE [SmartReview] SET TRUSTWORTHY OFF 
GO
ALTER DATABASE [SmartReview] SET ALLOW_SNAPSHOT_ISOLATION OFF 
GO
ALTER DATABASE [SmartReview] SET PARAMETERIZATION SIMPLE 
GO
ALTER DATABASE [SmartReview] SET READ_COMMITTED_SNAPSHOT OFF 
GO
ALTER DATABASE [SmartReview] SET HONOR_BROKER_PRIORITY OFF 
GO
ALTER DATABASE [SmartReview] SET RECOVERY SIMPLE 
GO
ALTER DATABASE [SmartReview] SET  MULTI_USER 
GO
ALTER DATABASE [SmartReview] SET PAGE_VERIFY CHECKSUM  
GO
ALTER DATABASE [SmartReview] SET DB_CHAINING OFF 
GO
ALTER DATABASE [SmartReview] SET FILESTREAM( NON_TRANSACTED_ACCESS = OFF ) 
GO
ALTER DATABASE [SmartReview] SET TARGET_RECOVERY_TIME = 0 SECONDS 
GO
ALTER DATABASE [SmartReview] SET DELAYED_DURABILITY = DISABLED 
GO
USE [SmartReview]
GO
/****** Object:  Table [dbo].[AdminAccount]    Script Date: 7/21/2020 11:00:56 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[AdminAccount](
	[username] [varchar](100) NOT NULL,
	[password] [varchar](100) NOT NULL,
 CONSTRAINT [PK_Account] PRIMARY KEY CLUSTERED 
(
	[username] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[Business]    Script Date: 7/21/2020 11:00:56 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[Business](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[code] [varchar](200) NULL,
	[name] [nvarchar](255) NULL,
	[totalReview] [int] NULL,
	[rating] [float] NULL,
	[address] [nvarchar](2000) NULL,
	[phone] [varchar](50) NULL,
	[detailUrl] [nvarchar](max) NULL,
	[fromPage] [nvarchar](max) NULL,
 CONSTRAINT [PK_Business] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[BusinessImage]    Script Date: 7/21/2020 11:00:56 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BusinessImage](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[imageUrl] [nvarchar](max) NULL,
	[businessId] [int] NULL,
 CONSTRAINT [PK_BusinessImage] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[BusinessReview]    Script Date: 7/21/2020 11:00:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[BusinessReview](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[code] [varchar](200) NULL,
	[rating] [float] NULL,
	[title] [nvarchar](1000) NULL,
	[reviewContent] [nvarchar](max) NULL,
	[reviewDate] [datetime] NULL,
	[username] [varchar](100) NULL,
	[userImage] [nvarchar](max) NULL,
	[businessId] [int] NULL,
	[isPositive] [bit] NULL,
 CONSTRAINT [PK_BusinessReview] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]
GO
/****** Object:  Table [dbo].[CategoriesOfReviews]    Script Date: 7/21/2020 11:00:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[CategoriesOfReviews](
	[id] [int] IDENTITY(1,1) NOT NULL,
	[categoryCode] [varchar](100) NULL,
	[reviewId] [int] NULL,
 CONSTRAINT [PK_CategoriesOfReviews] PRIMARY KEY CLUSTERED 
(
	[id] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ParserInfo]    Script Date: 7/21/2020 11:00:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ParserInfo](
	[parserCode] [varchar](100) NOT NULL,
	[parserBaseUrl] [nvarchar](2000) NULL,
	[fromPage] [int] NULL,
	[toPage] [int] NULL,
	[refreshExistedData] [bit] NULL,
	[maxParsedReviewsPage] [int] NULL,
 CONSTRAINT [PK_ParserInfo] PRIMARY KEY CLUSTERED 
(
	[parserCode] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
/****** Object:  Table [dbo].[ReviewCategory]    Script Date: 7/21/2020 11:00:57 AM ******/
SET ANSI_NULLS ON
GO
SET QUOTED_IDENTIFIER ON
GO
CREATE TABLE [dbo].[ReviewCategory](
	[code] [varchar](100) NOT NULL,
	[name] [nvarchar](255) NULL,
	[description] [nvarchar](2000) NULL,
 CONSTRAINT [PK_ReviewCategory] PRIMARY KEY CLUSTERED 
(
	[code] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]
) ON [PRIMARY]
GO
ALTER TABLE [dbo].[BusinessImage]  WITH CHECK ADD  CONSTRAINT [FK_BusinessImage_Business] FOREIGN KEY([businessId])
REFERENCES [dbo].[Business] ([id])
GO
ALTER TABLE [dbo].[BusinessImage] CHECK CONSTRAINT [FK_BusinessImage_Business]
GO
ALTER TABLE [dbo].[BusinessReview]  WITH CHECK ADD  CONSTRAINT [FK_BusinessReview_Business] FOREIGN KEY([businessId])
REFERENCES [dbo].[Business] ([id])
GO
ALTER TABLE [dbo].[BusinessReview] CHECK CONSTRAINT [FK_BusinessReview_Business]
GO
ALTER TABLE [dbo].[CategoriesOfReviews]  WITH CHECK ADD  CONSTRAINT [FK_CategoriesOfReviews_BusinessReview] FOREIGN KEY([reviewId])
REFERENCES [dbo].[BusinessReview] ([id])
GO
ALTER TABLE [dbo].[CategoriesOfReviews] CHECK CONSTRAINT [FK_CategoriesOfReviews_BusinessReview]
GO
ALTER TABLE [dbo].[CategoriesOfReviews]  WITH CHECK ADD  CONSTRAINT [FK_CategoriesOfReviews_ReviewCategory] FOREIGN KEY([categoryCode])
REFERENCES [dbo].[ReviewCategory] ([code])
GO
ALTER TABLE [dbo].[CategoriesOfReviews] CHECK CONSTRAINT [FK_CategoriesOfReviews_ReviewCategory]
GO
USE [master]
GO
ALTER DATABASE [SmartReview] SET  READ_WRITE 
GO
