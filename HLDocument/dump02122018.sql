-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: hlschool
-- ------------------------------------------------------
-- Server version	5.7.20-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `answer`
--

DROP TABLE IF EXISTS `answer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `answer` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` timestamp NULL,
  `result` bit(1) NOT NULL,
  `raw_data` longtext,
  `question_id` bigint(20) DEFAULT NULL,
  `vocabulary_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_answer_question_id` (`question_id`),
  KEY `fk_answer_vocabulary_id` (`vocabulary_id`),
  CONSTRAINT `fk_answer_question_id` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`),
  CONSTRAINT `fk_answer_vocabulary_id` FOREIGN KEY (`vocabulary_id`) REFERENCES `vocabulary` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `answer`
--

LOCK TABLES `answer` WRITE;
/*!40000 ALTER TABLE `answer` DISABLE KEYS */;
/*!40000 ALTER TABLE `answer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `authority`
--

DROP TABLE IF EXISTS `authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `authority` (
  `name` varchar(50) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `authority`
--

LOCK TABLES `authority` WRITE;
/*!40000 ALTER TABLE `authority` DISABLE KEYS */;
INSERT INTO `authority` VALUES ('ROLE_ADMIN'),('ROLE_USER'),('ROLL_TEACHER');
/*!40000 ALTER TABLE `authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` timestamp NULL,
  `content` longtext,
  `raw_data` longtext,
  `post_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_comment_post_id` (`post_id`),
  KEY `fk_comment_user_id` (`user_id`),
  CONSTRAINT `fk_comment_post_id` FOREIGN KEY (`post_id`) REFERENCES `post` (`id`),
  CONSTRAINT `fk_comment_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config`
--

DROP TABLE IF EXISTS `config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `jhi_key` varchar(255) NOT NULL,
  `jhi_value` varchar(255) NOT NULL,
  `raw_data` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config`
--

LOCK TABLES `config` WRITE;
/*!40000 ALTER TABLE `config` DISABLE KEYS */;
/*!40000 ALTER TABLE `config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` timestamp NULL,
  `activated` bit(1) NOT NULL,
  `title` varchar(255) NOT NULL,
  `jhi_level` int(11) NOT NULL,
  `coin` int(11) NOT NULL,
  `contenten` longtext,
  `contentvi` longtext,
  `image` longblob NOT NULL,
  `image_content_type` varchar(255) NOT NULL,
  `raw_data` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,NULL,'','Hiragana',0,0,'hiragana','hiragana','‰PNG\r\n\Z\n\0\0\0\rIHDR\0\0\0\0\0±\0\0\08D#ö\0\0\0sRGB\0®\Î\é\0\0\0gAMA\0\0±üa\0\0\0	pHYs\0\0\Ã\0\0\Ã\Ço¨d\0\0@IDATx^\íYŒ\ÇyÇ›”x†ü‡¼$FN”À6\â\'BÀ‰\í—I`\Ø	 1qQ‡Ø²qb%ŠKrI‘%Š§¸º(JT$J<\"’I‰¤HŠ—x,%÷š™İ½f§Rÿ\î\êİ¯gkf»«jz¾~ûŸo§ª\îúş\Ó\İÕ‡\'~\ê‰Y|\æ˜c;>®5†a˜A„\ÎÁ\Ê\Ê\Ê\ZQ6VV\ÖDõü€a†€\r‚a˜DƒxLıƒ•••5¢l¬¬¬‰\êù\Ã0\Ã0‰±Rıƒ•••5¢l¬¬¬‰\êù\Ã0\Ã0‰ñ¸ú+++kD\Ù XYY\Õó†a6†a	b•ú+++kD\Ù XYY\Õó†a6†a©5ˆ®\Èg9\æ¸\ãc69\æ81öü†_„Ÿ9\æ˜c¥²Ap\Ì1Ç‰ñœA€Õ‘\Ïs\Ìq\ÇÇµÁ0Áó]#tğ3\ÇsÌ±T69\æ813†a˜l\Ã$\Ä\ZõVVVÖˆ²A°²²&ª\ç\Ã0l\Ã$\ÄZõVVVÖˆ²A°²²&ª\ç\Ã0l\Ã$D·ú+++kD\Ù XYY\Õó†a6†a	bú+++kD\Ù XYY\Õó†a6†a	b½ú+++kD\Ù XYY\Õóƒ\ès\Ì1\Ç*fƒ\à˜c\ãÀ Pÿdeee(„\Ëz\ê\ÇB\Üx9\\ÙªWV\ë\êùAôİ‰ay¢­/\Ç\Î\Ål.\Çl[\çlˆ|\æ\Ø~|:\ÇAÕ—c\çb6—\ã_\â\Øw„>‘>ø‘»?I×—c\ç\âÀ \Â/X\İ\ÒM’_“‰u<?œü\ç ^:õgµ®l.+«eõü dc\ä3\Çö\ã?/Ä¹•B\ÌL\ä‡\ë=B¼öUº¾;\×\Z\ã»]\Õ\Ë\á°i)]g\Æ)ƒƒ\Õ-\íÉ¹A\è®Vk\Êá²²A°ZV\Ï7\éùµ\à\Ä^¹7?ô½\Ô\éIeŒÓ°A¸L^{16\Ç\ê\É8K`hˆ¬\îé“’<\Z\ÄS±z²:«l.k\Ô Æ®\å6ˆ¶R\Ïw9òuV/\'\ÃùUt=\'aƒp6\Æ\"A`W–\ÕM\Í\Ê NüecN?¬FLy€A\èÔ›\Õ	eƒp]³2ˆÃ¿+Dœl¬R=?`\Ü%¯Á´\Şø*O\èP\r…1coôQOŒ>&™G«\èöÀ.˜Õœ\æ\Ñ t\ê\Íê„²A¸®l¬\ÕóM@ÿ2f9š3ƒøP\Óx\åU´!\Äaƒ°cobÍœ	Œwyb²[².ø\\g¸D²¶¦{>+Ä¾\ßns?bª\Ğ<“B”o&s\à7…x\ëóÉœü6=]µL09Dÿ?\ä\Æst=\ç\ã\ÙO\è¯g\ÖÔ´\Æ `\áSòs\Ô °§\ÓÁtf\ÌJ\èTQıŒ\Z&ú\êo”J“™)µ \Ø0=¢>¤<\\\\¯¿YSSobm\Ì ğ¥dJ\îED\r\"#—Ñ \ç—TK74\à\Æ(*±\Óbº¤&\î©)C\ï\Ó\ë•\É6Süºjé††‘\é\ÄN‹‰»jA±…©VT\âP\â™ôºe2£\Ö ğ\Êoõ…o+\ç¾óiû\ç/\ä4<ª*\\ÀÓ‘ÏŸı‰jé††\ÂI:±Ó¢|]-(6`\ç?²^ıbòú\å8“¸¦fQ\Ù\àG\Ë\Ò,ª\èGM¡€¹‰\ïR­\ÜÀP¦“:M°‡B\r8ô½¤‚”‡\ãŸ¼~9\Î$öü¤=üœB“ğ \ã-‹…¨”U+70 I©¤N“\Âûja±¡:%¿?‘\Ía\ÆÕ­ôú\å8³83ƒ@\\.´\Å\æ\"\Şói\Õ\Â\r\r“r7ŸJ\ê´Á\n5\àğf²_)¥\Ëôú\å8³x\Î À\ã‘\Ï)\Ä\Õ\èÁ–|KµpCCù\Ği“Ô“1r^rQ)\Û¨_¿g\×\ZDÊ W¤Šş\Ô\èB;‘K\ëT\ë64 9©„N›¤.Í±«òûŒ3\Şø2½™Lğü_ığ—?üœr\\	OXÂ¡B:)F¾É¡xº>™³ ±\'\ãNğ}\×Dœz¤~ırœYl\Ä &p}EJn»x\Û\nùK:£Z·‰¡ZŸ\ÈY1rA-36L\r7ş~!\Ã\Í¯oS\ç\"C\ÆqYvt\áÄ¾/ª–mh¨Œ\Õ\'rV$õdÌŒÏƒ^4\ì•P\ë™\É#fp.‚(@\îAß½\É)E“8kÈ\ìÅœ¾Oº\âr!C\Ï/\Ğ\ëšIc1¶\Ê“\ë•Q<£\n\Ğ	zm›jÕ††ñ›µ	œ5I7g•>¾Oº j!\ÃÁ?\Ö_ÿ¬Rc£ğ»?5\Øö:rYµjCÃ¨\\^<‰³$\éDd´3“\êŸ)\rgÿKı³.HÀ„_ˆ¼³óÕ¢\r\á/·)p\Í5D÷dp\ëyšC\ß~z}3©c\Å \Æp6Q˜Ü±ÿ+ªE›\Z\"\Çş¦Hê©ˆ)SÿLiÀe\ë\ÔúfR\'0Ü°eX«Ø…Ùª\n’W=ıÕ¢\r\r•H\ï)Š§\Ô\ÂcCe´v¼´3^zP;°¶¬l\Í\è\ŞOÉ¤ÿW!\ÎHt´pFµfCCxıi¨\ë<ğ¿\è8\ãw\Ô)\r×¶\ëo¨\Îöe­S\ÏOZŒ¯\rğ\Ó.œù7\Õ:\Æo\×&¥)’z2°wAK±M{?CoS¦!\Ö\"dÖ±Ú\Û{UkstûˆNÈ¬Iºs\ç\'¨ñ‹†÷¬0û½M™†¸a\ÛT\\W<•\Ù\å\'©„Ìš¤Ü«A’n\Ïj¸Ô­¿Yg•\rBW_øE\Õ\ÒL÷`„$\İÚ«(©ñÁÔ\Z\É\Ğ0tRo;³Ö¨}ƒ\ä:‡¿&[Y\Õ]f&Tò½ÁP\\\Ä=D™¦\å5>ğ\ï%¦\É\n\Üz¾c	½m™D\Ø t9÷c\Ù\Èf\Ü=C2ñ†Ş‰`0‘‡ñ2¡\Û5iü\âõ\ãg\ÍkŸ£·-“H`]*a-¨o\ÛU\\Ö¾7TC“¿D.*º‡Uò\ÙP¼ˆ*L!i:¼|\'©>Yè‰¿\Õ\ßŞ¬¾²A\è*ºòü\Æ\æ(£W‚¤³.§¦Ê…j|€\×ôQ\Ód\Å\Õ\ÍúÛ›\ÕW\ÏOV‹\Ì\Èeö>(v¡¦tVş2“‰\'±¡¸¶*Ÿ4\İøuzš¬Àa\rµ}™D\Ø t8ú\çtƒs	ô` \él\ÕR\åB(5>€©Q\ÓdÉ®ô6\Î1S›<1¾®5ƒX­Ö‚ú±CU\ÆU½¸28^vÜ¼4xT&Ä–Ÿ¤Ë†®\Î\Ä\é¤I\à\rjº¬\Øÿ;ú\Û=\'ZÆ‹¸5ó1®l:\ÚX6.4dG™”	wD&œ\Zb:F¯E¼l¸¸,i|\Ä({|š,y\ïŸô·{T\æ—N&©\ç\Ñ/Ç“r÷\Çg£\'fp\Â•ŠV\Ğv¼s±Ó£tcs…ñ[µ	hKqñS]ù&\ä÷\Øc Æ‡]\'¦É\ë\Û\Zo\ïœÅ•-µùÏ¿ùb\ëQ™x­Ç¯ı†lX²‘»z0\ßV‰\'5\ÄtŒ›Å¨ò\áü5>bœ‡ ¦ÉŠ\Ò\Å\Æ\Û;gñÔ“sù\Ï7x\Î  ‚Ë®Q¹\é;\ì:»\"5 ’Î¦\â1sÍ–oğ=M–<÷qı\íŸ­J¨|Ó\rb>½¼V5¬²»ŠK—şO&›Ä¦\Â¨ò\\j<®‡ ¦\ËJ~Eû\ç@óe¨T´‚¶\ãÁwT\ãr•±\ä\Ä3­0*ªŒ\å\Ş\ÆÓ¡+”š.+\Î|?½ö\Ñ1DVñ\îÁ”HBWÁ³\ËD“¸ x\Ô\\¼Œ\è\êl4.`ŠO“%·z\Òim§gk\"Ÿ-\Ä5vY÷A6¨°Á;ªøuF¢\rrCaXñr\â-\à¦C/®¸»<§è™¡ê›†\âNRj{\ç45ˆò³ó75ù%\' ¡dH8\Ô?\\ Ê‰nM\éC…iPó±Á•\î\äö\Ôñ\Â\r\"üÂ²\Î\ZDXÑ´õ•_òD\átT\İP<f*\'®´Ô™>ª“òĞ„š—ipc™NûrT«ü£´³\â\Ø7\ä—»»y—+#™ú‘X(^\èK•³xVoú¨\âµ}Ô¼Lƒ«Aq>J·9¦3!k#Ÿ-\Ä\\I‰J…<›r|ñ¿\éĞ¶ƒdò9\à†\âĞ€*+®‘Ğ™>ªIó²Á›¥\ß\rÅ¾Aù¦\×\Z„eü=ˆh\Ó\æ\Ş\ërcË¤\Ê“÷TBIú•º\ãQsñ²ú]‘qt\ç‡\çL\Ä\çeƒ÷ÿnSmÀ¬A´@`¡sX\Ö\Ù=ˆ\Ğ	\Ó\Ôg\r»‹yg\ãû\ßrœ—/ù¥Æ’<Ìˆ\Ï\Ë\×67n_kU¢“”Z7ˆ±nOL>%\Ù\ì‰ô\İ6¨\è‚ôµO«=œ\Å	A*©l3z•./ö\n¨ñŞ¤ú›\Ô\Â)½v\æ .\Ì X¤ü„ªH\Öÿ¦\Ú\Ø9o®\êS%“C:r./\Î)\èLWÏ˜Ÿi^x€n[\ã‘{:tA\\^%7òP¾ğn¿J&‡´p‚.o\á$=ş|Š§UQó3Í¡/\Óm\Ëq\Ú\ß v«\Êd©¸¤—\Ú\è\íŒ2O#ÁL+\îÒ¤Ê‹=\é\ãŠ\ÛÂ©ù¦ò\Ş÷ôÛ›CZ•P¹§Cg\ÄsK\änj¿ğŸ^”p\"ğL\'‘‰=9P_\æ±+Ä¸š ¾ñùf\êÒ³bx\Õ\n1ñ\Ôız\í\Îzš\Î=\ì\ÄU‘&™\ÚzŸ(o\\\"Jk—‰\âšå³”\Ö,c–Š\É-÷\É]«EÁøo}^n`4\Ø§H÷¿!“Gâ¢–oÖ—y\\şOwú¸â½£ñù¦Zº$ıŸ‘\îes\í\Ëqğ#L\åmc•‹DyS`\áFÒ¡Ğµ\\Lı.¹Á\Û\Ztÿ!y\\Ï€¨+·Ü‹£\Æ\Õ½uó3\Ïğ†OÎ¶­\á\ÇWˆ\Ù.©ö\êr‚\Ê;]ƒÀSo-©o=ª2„Â¥GŸXZ“ô\Í2yöI¹qqˆ‘#\n\ï\É_\Ö\×eòH\\T¼0‡*7®’Ô™\Ò	fó4\ÈÈ¯×´-rÌ„{\rÚ±-­l§óNW6ˆñ\Í÷‹¡\Çj“½*÷\ê\ä	ü¢\Şı_wÁƒv¨r£\ç…\Z_fPó4\Èø±ÿ¬k_\ØKÅ‰À¤vlSıó\Zy˜¤Xd\Ö \"Tv,…\Õ\Ë\ë6D+­ş9rC·=8xO%‹Š=ª\Ü\ØóÑ™RôPó4\È\Ô\Õ=d;Û¸¤®»BUš•{:\Ø7ˆ\'`sLnY,÷\ZV¡J=_•ön¾Àı»¯©Ä‘Š\äq.–\à§x\ÙK\ç\Æ×Œqò3>OƒTGo\í\à‡JPÛ´µADY³X¦h |ø_\ä†EC\Í\è.ô\Éã°–¯Õ—\è×R<&?>O\Ã6†lk¥\îeAR>§’\Ó­J¨|\ÓÁƒ(Áˆ•¾P&/l~q6pø¹c$I\ß>™4—\Ïsˆ—{\0º\ÓSŠ‡\ÜF\çŸ¿xôÕ¿ \Û\Zğ÷\"4×”\æ\Â t+Š\Û\å#?Ô¦:k¤y\0Og\ÒM(›Š‡\ÇP\åÇ¡‚\Îô¤Ê½˜5_CL_\ÛK¶50uü!\ÎÊ½\Ö(/},HVK´½A\×\ï\Æ?ù?r#\İ\îlp²®\ïU™0—/\è¥Ê˜:\Ó\')Ş¿A\Í\×Uş\Öl²¢KtzûbQÙ¹HTw/ªI\ä¬X¸A¬Sÿ° ¥\Õ÷‘F\Ät¯üÁ;ÜŸ\Ğ÷J0w•º¼E—\ÏxLcşdŒö=b¹–˜8òşEUT{.t­c›–‡&Ï«¤NY«’¤ü\Ãy¿b\×Y¾ebh\åò\Ù=yè°Œ­DS=+?.ü—½¡sÀ1¼L?!\\W™°T²å¡‡\Öô-\ê\È\ÅúeZdòüSt{Q\\»Ü¿…@7ñu•2ˆb®1š?÷<\"KW\ßO*‰\â\Ö/ÇŸ.aFô\ím¹R_tu\êNßŠ¯_¦EfN\í9‰\Ñ\rKƒ\äN	\ß \"9\×Ì²Uƒhj\ïA2öú_\Ë\Ş\Û\Ù\ÎIp	\ÑŠ·f\Åë€®N\İ\é[Q‚Å—i™¡\îOm:	\ÜxX\íYD&|³À Fºù‡Ô²\ZQk\ë#Ÿ3Kk›\ïÖœ8³†\\ùÅ°üuD\"ô½\ÜŠò\Ö\Õ\ã†\Z§Át\ÕÑˆ\åÚ£ô\Âmºu&ñBm\â\ëÆ•]‹šş1±f…®%d\ZQ¹}@®lÙ¸:<øIp‰\ĞŠôRõÀ\ÍW:Ó·ª\Ã\ï\ÑËµDù\ímz>F\Ö/«Køfb\ßN\ê\àùI&nø\Ù@Œ3¦T’Zõ³BŒ]NRv2web\İ\Ù\Ó> ¼T=ú\Ñ\ã§K-\×Sw\íZ‡\Ég\îŸ3€¸*Ád\Ô<u±c\ë‘…iDq×—\È\ßYHƒ\Ä/$\àv,!œey)cGW\'9~Zq\Âr-Q-œ\'Ûµ\è\"m\Å ú˜0g)­m\î\Ú0v\àa¹¢±Á;˜Ñ‹²ñ¿\É\Ğñ\è…úº\à*Ë´\æŸ\ãi|¹\Şø Ù¶u˜À^Ä‹1hO\ïH\ç\Ö;±¦¹\îM0yn£\\\ÉW;<\æ>š\0\í\Ê¯\í@›&¸\â4¾\\‹Œ¼ü\'d\Û\Ö\',k\Ì`p\ã5ŸfñğkN%q+Œt/öûY«–\Êİ¢\åbh\åŠÙ»3ñ\İ,Áw\Í~\æ\Şr¥w\Ã\Ø-Q%@)^0¯‹\ßÕ©9}«Š§\âËµ\ÈÄ»?\"Û¶.\Õ\ç{	\áBø9Wv¥wã£‡?¥5­›\ÄÈºE\Ò–f›yZø}ûˆ<¨ \ÍÀ\ãù©ú ©ñ\ÓeQË¶Àô\Õ\ç\Éö­Kyó1şÔ’@Ÿ¾_Lm¿OTv/®3\\ºMM\ß\n¾A\0üúSˆ4¿«²\Åş\Õfyş÷ƒ_œNN¹ı\Âw\"Ÿ]qU<Œ–\Z?\Í{/Ô²-P-—ùò1²/<–ql\ãR1ƒk&¤A,´\ç\"Ê¬A\àB\n\ì\rf9µz\áE+Œ¿ı}r…w\Øu&@\Û É©ú\àšrüA7+µlKş,\Ù\ÆÓ¢´.s!³pnÀ7<G?Aq8\ÆS—¶Ê•{¹³Ác\î\ï</½¤\'%\ãuBW§\îô-«4	Ü¿_¶%\Æö}“l\ã®Rc\0\'“bxUº\î¤Kuø´\\¹\Ø\Èºo?×¾Ÿ¨¯\î\ê¤\ÆM›\Âñúe[b\â\äO\È6\î*uC‡\Ñõx…2E+=i0¼ñWƒ•\ëÿ\nt°\â\á+T\ãoŞ®¯N R\ã¦\r\Ş´^\r\ëôõ½d;w•:ƒ\08ŒˆšC¡k\áWdµ\ÊÈoÈ•{‘A#¿%û­m\ã1ıT½p(@ŸfŒs Ô²mP<\ç?×„j\ë.B\Z„¿s\Ø\à‰b55}Áñ\è\È¦ğ<\ÆÛªá·£\âKª^x†ƒ\ÎôU¢Q\Ëwœ©ó›\è<2i\0\Æ0òDó÷L\ÌGqûC²\â2\Í\âw\á\í–\r^ÒŠ]}ª^~W\'1~ÚŠ®V<3N\á].G˜¹÷6™G¦H4\\	™\ÅI\Éòş¿!W\Ó\0«\ë&‚\ËZ:]_7¿«36IÅ»6\âerŒf6“&‰‘§»‚\Ï\èƒ\Ç\Ü\ß|V6jI;\ëĞ»õu\Ã;<u§\ÏJqø/—CŒ¾øGd.™À¸ATn½.+}i\ÜttsWĞ o)m\ÇxğP}\İğ`\İé³Šqø/—CŒı!™K&0jş=\Ä\n`\æ\ÏXô´¤/Ì©«\ßYù}˜°±ñMª¿/›L_\ÚN\æ“	Œ\Z\Ä\È\îß“–\r‚i\Ü\ÔtsgĞo)mÇ¸o]?œ€Mcş‰\ÑKD•\ÍªC\Í=;MŒ\ZDù\à?ñ`šıù½²!£Q·³\"©ú¡«Sgú¬‡;Tù °ù\ÓdNeQƒ˜:¿¬<Óˆ3A\ÎT\Ş\Ã!1®ip·l¼l0úÊŸ‘9•5F\rb\æ\îYY\Ù\à}ğ˜›;\ä/œ$:x¤¾‡ô§\ÏT¥I \Ç(^>˜8¾°‡Í´Š1ƒ@_nP\ÙÓ¬\Í\è\à\á \ßÜ\í³¾¸«Swú¬=\Z\Ôv°¬\ÓW{È¼\Ê\Zc1ú\âª\Ê2M+\r\Ñx{Ñ€s x;w¼\èAĞ>s•&\á\ïE\Ä\Êhyhf\ãc\á?ô—µ2Í\ÇÆ£ñönË‡\â\æ,ª\ènÔ™Ş„b/‚*£eJ[?G\æV–3ˆ©‹[d%\å/\Ó¸\Ù\Èo¼yA&!UO<Ø…\ßx~UN‹Œ½şm2·²Ä˜AT’•f\æa¶\ÑnÍ¿[_Oz\èNoBûöÕ—\Ñ2“§%s+KŒD\á\ÉO\É\n¾\Ç4\Ëğ1\Õ`sN¼\Æ\ëŠs-Ô¸Ö&\Ç\Ëi‘J\ï2¿²ÄˆAŒ¾ò§reŸdš¥ÿ 7™£7ò¹c˜A¼®~Wgd\Ü\èø¶b\\]/§UNøï¨¥r,+Œ\ÄÄ»ÿAT–™Ü»m°y\'^\ãu”{KÔ¸6Á\î@—\Õ\"¥]_\"s,+ŒD\å#\\€r‚i¼kòÆ–9z#Ÿ\Û9\Æ¨úú¿\à\Äø6cœ¡\Êj‰ò[\ß%s,+¼\âº\å\"k*—7‰Ê•g¤QH\ÚDE\ï\ÓBÜ”\Ø\Ôÿ.\Äù\ï\åG\èú^[-\ÄGqU’ •]\Z\Ò«µÚ‹)<ò0™có²V\Ñ\İr:O¼\ê‰YöE>s\Ì1\Ç{\âÿ\Ùvº Ÿ\×/ñ\0\0\0\0IEND®B`‚','image/png',NULL),(2,NULL,'\0','Japan 1',0,1000,'japan 1','japan 2','‰PNG\r\n\Z\n\0\0\0\rIHDR\0\0\0\0\0²\0\0\0O\Û\n\Õ\0\0\0sRGB\0®\Î\é\0\0\0gAMA\0\0±üa\0\0\0	pHYs\0\0\Ã\0\0\Ã\Ço¨d\0\0/CIDATx^\íù“$\Çu\ß\ç/±I‹¢€8ü›m™»‹ƒ¤­0M\n ([°(	$E1l\Ë\ĞREJ°AÀ0h’¸D÷^8¸÷b\ïû\0»À.€½\æØ{¦™\é\İòû¾Ì¬ÊªÎªÊª®\î\é\îÉŠxñ\íOYW¾—/³ª»û.}ñŸxÉœ:u\êT\é\È\ã?ğú.\İùOy†S§N\ê\ÊÁ#†6Ó±cÇ#™™cÇ“ºn…S§N\Ú\× s\êÔ©S]Ep HZ\èØ±\ãe\Ï.sp\êÔ©Q]pp\êÔ©Q)8|Š?ô\ß%Õ±cÇ‰9s\Ít\ìØ±c\â>1T\äp\ìØ±cR¿[\á/t\ìØ±c\â>5S\è§ü•;v¼¼9œ:u\êT(‡1|s\ì\Øñ²gD¤ğWr\ì\Øñ²g\È¥:v\ì\Ø1\ØÎœ9s¦[ŸŠƒw}ZªcÇZN—«–7¯ñ®}ÿA\ëõ.\í\\ñiş\àty\ê\ås»‡iv\ísV\ë;]>J™ƒL\'\ÔL\ÇËŠ\'ş\á/98Ô¯Y­\ïxù°\Ë–¹Î¿wˆƒ¦«ü%\ë\íœö¾ºà°ŒÁ@Ÿ\Ê[\ÖZm\çtyh\ßÃ§=V2\ÇË‡õ¬AM#\ßúra\å;\în–Á\áS\á•÷<\ã	…iªûĞ»ü•\ÛR·w\Üû,‚T™\ã\Ş\å\á¯ÿ¯z`»\æ©6xÁû¯h\Ü\Şñòa×­Xf<ùø_\Ë<\Í\í\ßV\Èşw/spöÏ¤:\îe\ÎL\Û;^>¬g\ËÁòg\Ë\Íú.Ëˆqùn©{š§ÿtÿ\äI‡¢÷\ï¸{\Ø\Ï\ÔLÇ½\Í\×ú=¯>\Ø\ïÕ‡\â­ÿ‚7ó‹ÇŒ\Û;^>\Ü\çG\Ò\ËR÷6\ã‰\ÅÜ\İ\ŞÜ¡}F›ø\ÉI\Ü\Şñò`×­X¦\\Ş´\Ö\æ´\Ú\Şq\ï3g*b`\ã\åÁ\å7^6‡\ê\Ş\íV\Û;\î}§\ËE«\Û\Ş07\\°\Ú\Şi\ïk\ß\0™\Ó\å¥x\Z7\Ùl\ï´÷Ufr¦ŸV8\îu^»*CA\ãd³½\ã\Şg—9,SM\nWÿ_Z—\ã´wU9\äLÇ½\Ï7\êu\n\Z\'¼(•¶½\ã\Şg\n¿\Åœ./Mšfş_\r\ë;]~Ê™Cx¦\ã^\ç‰G¾%Ã€y\Â`e‘ûsÜd+\ÕBÇ½\Î\È’¦\Ú\'§İŸ\ã\îd×­X†šô+m\Êq\Ú\Û\Ú7,#†\Ğß’ê¸—¹>tQ†ø)i{\ÇËƒ#ÁÁ\ér\Ğ\ë3S2\ÄO“ş‰uyN{SepcØ{™m¦òšg\nÛŸ\ã\îd×­Xf<mûc/¶[•\ç¸w™ƒƒ³\åc•-k¥û\'O”4m\ïlù˜2bø\æ¸g\Ùf0RM¦\í/\î»*g\\]ù©{™o\ÌU¤\ë§O”,zÿ»‡ın…š\é¸wù\ÚÿJº½İ„.HRy{›)sc\Õgx†\ã\Ş\å\Ò3K··›ğ¦d‘ûw\Ü]§=¯\'K··›\Ğ±)\×iojŸ1œö¼Ú¼üÆ¿óU\ëòö–r\æš\é¸g9\ÏT^ólly{›ûFx\æg<§½­3?}Xº{¶	]›òöú\İ\n5\Óqoò|\Ê\ß\î\ÇM\èŠ±\Ç\İ\Ç\áÌ\Ìqoòõ±\é\îÙ§künjù{]·b™h3\Æl÷\ã´w”ƒƒ°ß–\ê¸×¸ôl¶÷¢Sı“B\åE\ËwÜ›¬g½jY\ßoˆNx\ßÁT®³\Ş6\n\"b\\ó#‡\ã^\ã<\ï7D§\É\ï|5¶|Ç½É®[\Ñ\ã<ú\à\ïJ÷nnR\ã\Ñò÷.÷][-f:\íM­S1-]LÜ\Ó\Ş\Ó>•Nø3÷c0±i±nµ?Ç½Ãœ9\\“€{‹o\ÌU¥w7?M?ú§©ûs\Ü;\Êx¦\ã\á\éGÿDºu1Ş²,òøw6\Ë\Ìá·½Q¨2\Ç=Ás[\×I·N™¨\Ë`3\á-\Ë,ûw\Ü\İ\ì§½§D´™²¼Z=öm½§İ­}*bŒ®ş¬TÇ½Â¶S–/e•Ÿ{\Üzÿ»›Ep Há´·Nl;\Í\Æ|ûú\Äu9%‰	O?lö\ï´û•‚ƒŠŸ\å{ƒk\'Hw–9ù\â\à\ïFiV\Î&¼ı\íZ 0\ÔNöj§‡~q|;ŸC\İ\n±\Ğq/pôfı\ã\Ó\ì\ì\Ñ	\ëG\Ç\'88q¡M\Ó?üÓ†ı9\î=\æÌgŞ£:\îv†óF§ú\'\"8p& M7f§y»…ƒ;\ä1-^Á\áˆw£\Zş¯<)òxw&ÁÁi\Ïh\Ã#L\ê õ¯|ªÁÑ‘1`»òs?•s‚	Y2\è„/r\Ù‡\Ó\îÖ¾1ŸõœöF\Ç¸+q²\è?^!cÀv3ÿ\ã\Ï\ä1©õL®OOz7\Êá±Š‰?øB\êq8\ín\í£ˆb&©\ã®\æ‰o|Aº¯˜\à\Üõógø3œ<:\æP¡ŒAm¯O‹—>\æÏ¼u3ô©ºö9\ãş÷»Ì¡\ÇN«Oªg¿±0OY\ÅUf5!cP\ÛcüAMX\Ê‡±;’~<N»WepP‘C-tÜ­lú&œ=)!üŞ‚¾}ô‰…\n<Vı\ŞÅºqÿ{‡98 •»\çwx†\ã\î\æ\Ğ÷$( 0\à‰C\íı#\Şõ\Éğ#IŒM\è\ÛGŸX`ª}€Gš´=i4@ K’v<»—µ\à\à´\ÛÎªO\èB (¨\à°x\é¹DL\È2ô\íKO<\"—ˆ‰_„Ò¶¯Ÿ?+—ˆ	óM\Ç\á´7”‚ƒˆ\ã~\äpÜ­g\Õ\'\Î\Z\à\ÜÒ¢Áa~\ëº\Ğöx¡O\×g&C\Û\Ãô	/Z5s¼;›Ã™ƒ2\Ç]\ÉÑ·\"o”f„SSĞ¨_ V?2\ŞPzò‘†ò¢e +\âg‘\'˜0 ™÷xw6÷©ˆÑŒ^Yù9\ïÊª›„®¼É»¬3i”¡\Ã9yiõ¦–ñµÕŸóFVÿ7Jj{\İu¼§ &Dšœ\Z“©œÅ¡Kri0q€À˜›\è„q\nS9N»_	w\ß\ä]Zq‹wñ®[¤\Ş\Ü$\Û\èç½‹ô¹-\Ì\Ú\Èøœ\Îj»€¡ı1Œ`a{\İu&ª—–üG™‘ÁD5™Zö2k\äe¨3S\Ærœv¿rp\ÜU–Q\ØÀ\İ\Ê)HC\ÎE\Ze©\Ê9Œ|w<³.1÷[²rz\î\'EŸ/\Ïı€“\êZz5ÁÁ¹[¡M\è&˜\Ê+?ù¨\\CL\×G.³©	Y\Æ\"ôiú»ÿ>óñ:\î|e<3\'£R÷¯P@³™C1|\Ég¡\Ê\Ì,œ´%\Ì\Z\Ï0‰‘UŸ‹½¾I<EÎ©Op^ı±%:ÚµÀ\ËR¦ò&ÿ\à‹r\r1!S\à@£\Æ+H£/Ra`S?¬\Ç\ï¸3¹OEŒ	9š`ô›Q\éUK¯œ \Úò§1k„\ãZ\æ–ºTœW/¯¼%õú\Æñü\Öõ\ÒM\å$Yµğ\èD\ßq˜ùó¯Å–§¿)©Ş•À\Ä]ŠÈ &&d-Y·[õ÷e\èî›…’\å\â•^%>›™”\ãOEOV–Á\"Eha>Á-g³-¿\n§\Ê\Ã\"£\ÈÀl),¼‘¥FXÅ£÷ˆ¬!\éú\Æq´K‰©\ë\àhN\'I\åE‰ªm1¸‰¯~›\Ä\ä7¿[^·òÈª›¨»,º{­\ÑÏ‡x \Âğ§¤\ãk5÷©ˆQ„ÁATz?sˆ²T\åF¦‹\ÇĞ¨s\ÅqjŸ¿CtL›\ë¬+2€è„–a\âñc\ä\İLx\"‘Tnôû˜ôwLbnº)\æòºUƒà œ6¬3gc\è ÃŸl³U\Ú\Â\à°ôj;\æ`\ÊpCmQ>•Y#L7_\ç¼ÁN©O<TN¬Œ¿<¥M\è†$•;ı]CÀ¡ £—\éWCN8q\åu«¶>sH\Ö\â\0ŠP¤Æ¾“\ÑÉ±\ÄÊ™\Òx)75C\Çî¹©\áz*­“3b\Ğd\r/>U+ü>‚n\ÑV\İSYºE§h™\\nd2•£\Ìt^®#«Epv«ö9;Z²®ğ\'›\ãl•RpP‘B\Í\Ì\Ï\"8¨–Y8A\'r\æÌ€5…é†šYj\n#sˆ»¾YşW¢S§<õi©Y\ÜS\á\Äá–½y\Ç\Ìr¼Es_0³y\re\Í*]œ$VÎ•Æ©\Î\ÙF\ÅM\ã 84jo‡\î\Ñp\æµp\ËßŠL¢2û•\ÓT«G;QUI\ã\ÜJ@\ç¤\à°8pÑ»>3İ½6;c<¯N×†\ÌAj”}•™AQ,2‡ô\ãl•öMJ˜¼ú9¯¥~s¸ò\ãB¶™Y—†q,y•mü^Œ9˜¯o\é©{s‡öu­U^{&W}ZjY}3µ\àpZj\ÉWŠ–½õ,•şT\äùd\å 8 ¶™;GN\îX¥\Ú\Ë\Ì!\îúVÿ?F\Ç\ët«nx)ñ¼:Y\ã\ÆĞ²§\ÎÎ¬>\Ë\àe¹2›\ãl•öùƒ3šaŒ¸«±Uù£\ÖpK\Új†#\æc¡­\äq\\Ã”\ë[}õ£vª\Í\ïÜœx>\Î\×(s`\ç&\\©;¯hÙ­Xm—ƒ9sH8¾Vs\á\İ\nTt\Î\è\ád6\Ì\Za\á4\é\ëFp°¹\Şs{w±\Ólş\ØAo\ê›wfª?Æc\Âq£Œ–ŸU±\ni\Ì\ZÃ£´ÿ\"\Ï\'+s\æ€\Í\è\ÕU7{W)\Ê^YysP\éYU\Ë\Øn\nÇ³\áN\Ğaº~¸ªŸw½¹{qp¯p\ÂN\Ö\ío%G+tœ®\İØ½ø\Ê{1Šû\ÑLËŸU™ƒ\íù­…8gl&£pŠ<¼”ŠÃ†\í•,\ÂI×»ü?ÿJ8_‡[ù©\'G+t˜œ¥¿†Ü”*3°j\é³2k\ZC)hp\æ`qŞ­Ò¾)	S÷‰\É\ÃqÎ”®\Â9\Ú\Å\Ê\Ó\ØwVe47,™o\å\Ï<¿	F–t½K\ßûš\ç]_\ìx«<ùC\ëúS#8\Î&[`yı¹\İ\ÜD&Ì¡\Èë“•ı\ÌA\Í\ÌÃ3RKO7¦P–\ZÇ¤*¨¤1À\ÄAp0_\ïÊ“z7\êu\ï\Æ\"Y\ë\ÂÎ·\Ç\ßJ\Ö3vRÒ¶²²¬\Ê1±9q}²2e*b\äWô\Ñpbpl\İ–…*,Zf\ë-w\Z\ã<S™5;\Ş}]\Ó\äë½°up\Â·ÅO\'G+Tdª–\Ú,/¡Š\ÌÁşü‹\ÖB‚tü^F’“’£\Î\Ú[ªZú46)nx#“iŒ¡Ò®ó\âÀy\ïF­\Öñv½4›x­P?s SNå …VÜ¡\Ú)Á)DŠ›ƒ\Ên\ï\\\Â9\Ò8pª6³2š–\Ì	™D\Z“\Şss\Ãõ4\éù9r¾…®°ò\ß>\Ôpü­T~º œ+ªªEn‚X2sŠŒ\Ü\æ¼[¥\Åªø¡ \Ğ,gP\å\Ôi\Ü	Š\n¤óg\r\É\×wî¹Ÿòo3t‹\Õ\ï2G«\Ô&sˆ2±XÎ¯(K\ç¥÷nò¦¥6\ÃÃ«De÷ûüR\ÓŠR8+c-u³¬ö“È¬¦– ‰1Öv}ûÏ‰\ÌA3ü\Üõ‰Q\ï†TübS\Ã:øı†«C\Ş\âğ\ÔÁLŒ\í£eò\ïRb\ÚşñÛ“ú:\Ø.Kıi–¯RıC‹‹kZ„²³Z0t¨<†l2\á|[\Í~\æ0Íª,7f¢\â\Å(;-u\çò¤oˆ»¾³xÿ°K\ÔğC0µ£{½Ú±½^ıô1rÖ‰†uöm\Íu?Wx¤¡L\Ø\â¹y¿jÿ\×\'\Ç\Ö)ÿ\rº\Ùö——\Ñ8\áº\ÂTe8^˜—N9Ä±\n	\ç\Ûj\îS£fQUÎ•¡¸`6\\¨’\ÙsB&‘\Æd*sˆ»¾[×Š_y’v}ŠJ­|\í\èo‘ÃÒ¬˜‡V\\[·òÄ£±\å\Ú\è“V~_B\íÙŒ¿,—Ç lñ\ìI\ëı4«EgQU-{V†\Úfº\"8Øœw«´OEfl\Ìr±p˜¬ŠmmŠh\Ã\íR?8h\×S\çÅ©	\ïz¥\ä]¯–Yk\'y×§i^iÆ«‘Bùó‡\ïz‹#\Ô\ëA\á\Üy\î§\Îúşi¿\'öy‹“Ô­ ®_\é§.È€8®³\ï{×©{\á\ï¿<S\Èşm\×\ÔwÒ¶³\ÔŒ²¢™C‘\×\'+s\æ€\Ó÷‹(\Õ_]-œ•=úA\Z«–4+\ãB.ã³‘Yf“•!\'ï‹¿¾Ujù\ÙÑ¤Áù\ÑZ\×/}\ÌG`8….\Æn\áœjş\èp\îû©ó\âÅ‚2\É\ê\'{\Ç)@P`ğ\çS6ı\×Ï‡Ö\ßüzCy­`\Ô?\ß\ÉÚ¡+\æ?³\Æ0^\ÈrşEs¨[!f\æg<J‚cdm\É[\ÍP8 \r/•\"sˆ»¾õNSL&­ö\áIo\á9\á¹3Á¼÷z‡w{µw)£\ĞÖ­ø^\îû©óü–5¡rkg\èh8¸¤y‹“\ã\ŞÂ±}tC\ë.NŒ§–_‹n…¸¦pB¡ñ\'\æwGºE_¯4¦\à \"Fó*2\Ñ\Â­ª\åN\ã¥T\Ü\Ô<\r2‡°–ş\â÷ù…\"\ß&)¥?¼‹\n^Ÿ_?}‚\çs \Ñ\æÃ©M\åf\Õê“”½h\å.\\\àı\Õ(@,NŒóÇ®yG÷Š€¡­Ç°6ûiFU\æ€\ë©;[\'¨\ÊBL*Ê™Cúy·J\ÃİŠP\É\ÎA\æ w/\Z£ó\Él£\ädM0;©£¢µƒ98®oRw5\0«ŸûĞ«]\äÏ‹øŠ\ßŸi\Ş\Â\á^½ÿ\\h}<-\Ğ\Ëk\æş\ê\å.^»\Âû«>.˜ö_\Çx–OOòq†Ö§\åi\å7Ëœ9\Ğõ\Ä5…Ó±¦qC&‘\ÎB\ã¸8U™ƒ\íù\Í}ª\å*W^^ÎŠJcÃ¬t³u\Æ\Í\×yJf3¸IRgÿ\Ã]\ìh<† mqôªøŒ`pˆœó\Ğ.\ïúø5·@ı}9lz\"T^³º8HAI•ı\Ñş\Õ<<F\×Ïò®cğ2r\Ü0*›ı\äU}\ÌÁ\ØR¸)¥À\ĞJF\æ`sŞ­R\nAË®úê¶ŒOŸfrm>;M”ñ9\Âp”–3k/š‚Cm\Ï&ñS\ÄğıŠ…ƒ;\É\Şa\å®\Í\ç1m½úÅ³Æ›Wk»7†\Ë?óø<6:s˜­\Ë\ëp\Âj?y5Ú­HS8¡\rû\ÎkÁEj‡[g¦ƒ2±\î\\F&«ôK©¸\É\İÀP\Üı\æ,N{‹\Ô\ë†Á\Èù;\Ø\çÀI1Y\×İ³9T^´ü¬\\}öñğ>&\Æ\Ä~>:>:>Œªåº•¿÷õÂ\'\Ê#«£\ÎMrºtÖ³\åL–\Ä÷Ş’x¾­\æ¦2‡(#h\Øpúƒp\Z3ªœ,\ÌC”š\á\ï\ËG¨ÁO‹£5Áßš#e3­oÅ¬y\ÙN£™\ÃükÏˆ\à@)º\ÒúğWû\ä¯öñi©d’\ë£\Ã\r\ë\ã\ÍFıf7«\è\æ\è\å+­?\Óp<¬4?º>\Æ(l÷—U9s \ë‰{\0GcMcM\á”6\ì;sÎ¢‘9 rFU½ûŸ\Æ~%/Š3(n\Z‚*\Å\Ä}\"\Ê\Î|IF@ƒN“Q4¾²J\Ü4l¯\Êi·F÷?…cÔw\ÑwöüwšQ|\ïÂ´¯,†\ìÁvYTeAM–\ÂM)Y3¬,9s°8\ïV)gh)‹Wrb9DŸ#G±ad£÷È€ Ÿ”%\ÃÕ»ø\\.\İQ¾Tb,³a\Ö&\Ùdœ5PŸ=d£W½ú\å~£5¬\ë?:ß¬\×\'\ëxUÚ´¿Ç·°kcaÇ£sŠR2\É\Êi\ÓXh1¬ºq\ç\Ûj\îC\å;_6)\\«N­NbVT^¤Œ\Ùn²\î´q\Ü*Å£#u\\õ‘+¢¿®Yı\â\Ç\Şü¾\í^mÿv\ÒmR·óüèº°Ú¶u‰\çW\ç0\î`\Ø¿\ßpl\Ãñùc¡uG¼ò\\aµ¿,Š\àv²W²$Fp°9\ïV©Ÿ9 ‚B;U¶ NBE¼f\İ\rt5°/µ?(Y…\Ç2\İL\Ål>UA Up˜ıioq|40\nèµ«\Şü\Şm\ìxPü\Õ.ú\Ë\êWB\ÛUÿV<6,\âú\è\\\"§\Ö÷S¿zYd`:¼!\É\Ç\'ƒ•j™¾]m÷&«ıeaŒ3\áº-±¸Î\ÄaUÁÀÌ¨\ë\è.#H ƒ¶“E\æ@Ó tpi¬*w¡L\Z\Ç\ãtĞ³_RÁADÕ¢x†B\í7µ:}¿8\Å\á\Ë\Ü\n+«Ÿ8@b˜?óW£\átûwx‹pH¬‡<\"\ŞHô·£y\êüp^j?Êš\å\Ú\ÊVÔ¾ÈªwQs\Î\ßw•§ºg\ÙV¯>$s\áı\ãşy¨õ®<°*\ìd\ß\Ör\Æ\ç\æ™\Êm3C/Gy•™\ÕzyX\Ë\È	µº-LŸmyü\Ş\ÏK§V-…ròb\Ù\Ï \èa\ßşq$0/+Ep˜•²J¹•\Õñ>Ã¾-¢\ë@\\\Ã+\Ò¨+1xI¬CY¾€\Å/#i\Û\ÕNó\Ï-«i\ÍhiÓº\Ğş\æ¨\ë€@°ğ\É\Ş\ÂûÇ¼\nu+W\Ñ\Í8s\ÒgXå¡r\Ñbš8hYÓ¹X…³u#Jd\n\Ú|\n\ÏšU\Ú	n²\r³&0\ÒETv8r«NŠÁNUI\Õq(n•b¿õ+ƒ^{\Òğ­Ç…½[¼…\ÓÇ™kø\í¼¦Œ\åxCò\È\î\Ğreó¯=\íŸ2T\ì\'»’\é,m\ì\áo‡÷÷É‡^e÷¶ú¬Ï‡\Ö/Š\å”õ`<E-¯\Ñgd(Ÿ+-™¨¼¦û‘\Ì6J-pF\Æg\Ö*¶S\Õ\â§q¬š3rV­¥Nc\Ö6òej\Ége\Ë\î;q‹yò^´¶\Âi­•.h£B%ñÜ«¿sm˜µv\éoú\åó{6óKEj>´F\Åü¾­şò\Úù³¡\å•\ï}\İ?½[‘¦*\Øp\rc\r\êx†.y•]›½ò®M¬ó\Ïü™÷ı\åó¼\çÏ‡rö)?¢B\Ûp&UA¨ \ÆÀw˜…¥ñ\Ğq$sÀ—L\ÓŸópP)\r\Ì\Zğ\Ô}¢’—¸²\ß\"µõ|uµØ¿8\àxZ\Åõ\Ë\Âi¤a|Î¿\0;D}z5_†…=\ähXFŸõ\í\Ğ\Ñ\ÏTVi\Z³\æ\à¹÷„ö[¥c¬\ìDp #§n…Z6ÿ\áI~\ãÈÔ¶\ÃC\Ş\ÈC†\Ëg\Í\Ş\Òge|\Î\Ã0\ÕÂªùq\Ü6µC\È\ÊşKP¨¨¦\ÃZwB9m»x†\Ò|8-#p\æ\â:ı\Ò\Ó\ä(h‰…\Õ.~DÎ¿‘œ£ø2\Ó±l\á\ÔQ1_-\ïPhÛ…½›Cç“”9À\Ém\Ø\ZOÿüñğ~\ÏR\0Ø¹‘‚ÀF¯İ³%¼œ²	µ|şô‰\Ğ2ŒYD\Ë\Ï\Ë!m²¥GÙ¹X–£,UF¡ó\å¦N\Æ\"$q9ĞŒs\ZÇñÙ†ƒJ\æ)rPQ\ÉI¿,µM§\Â½´\âv\ï\â]·{—\Øn£cƒ‰ŠP”\Ö/‰G‚\Òx,Y¾T…ŒóûÏ±\ÖÎñ\æ\ÉÁ°ŠG…ú¶Õ¿y(t>0} \nû\Ë\Â!%S<üÀª\Ğ~\ëW .g¤s\Çˆù\êø\É\æÏ\ËwS\à@ö ¶%»FÙƒ^~h&]••#™ƒ\ã³\r\ÃTË›Æ¨?\ï¼İ»ğ\Å;¼ó_ü\çŞ¹/\Ãg\Ì\ë§z6H\ë˜Zòvs\ë$›\Ô\ËtsgU-a\ëù\Zu\'pƒ\ÔM3\Ùyº‰¸Á8NT\ä ¸e\çÊœZ+«ÿˆœ\ë7\ŞÂ‰ƒş¼…³\ïóE\ÌCı^•–\ÏQ+ûÛ’ƒ\Ş#\Şc\ìOT\ì8Æ±ea?û†Uön\ãÀP\ŞA\Çÿ¡X6\ê¸7w<8—ù3§\Ärš¯\æÁ\æN\æòQ1ı–x©™¬(\î_q\×S}2Ù…;\ï í„£f\É„\Ã}¢§\n\ÄJ\Ö&Æ…X\\H\Ç\îı<;,ZÀv\èÄ½”%\Ğ\r1İ¨$CV¡•K\é\Õo}û\×x\Ä«ûvx5r\î\ZZWjI¡x‡\0CÁ\ç\ÎŒuÀ¤úzµşó\Éû+X\ç?ş ¼>ö³\âø)\Ò\ÏÇ¦?\æû\ÛI­\îß©•OF\î\'Ê¨\ÄÑ–Ûš©\Ñ)‚UK«\æ\Ç2µ\ÄptSİ±±Kw\İ*¯…\ÃÛ°\Ò\Än;u”\Õz\Z·BU—\ÂÖ¹›\Ñaº¦ckhpTÀñ_ûş\ÎÁ&€\ãb8h\á“UYVöŸ÷\çeihùõ®C^Cw™´^¶b8r\"Çª\n\nñ\ÉU•<\Ìdm\â™/…3‡ò—[\ÃCw\'w!l\í<\ÙÀ\nªò<Dp\è§T{«7õ\Ë\'¼\é_>)õ	Ö™WŸ••>°…KŸøŸ1h7wòˆ7ò¨\Ğ÷I)\ëcV\ë²QK­ÊŸ£m1ovı‹şş¢ûW¬\Ö-­É¸\\g´\ÜA¹b9\Şeğlºó‹y|œúñ\Ëó©\r\\m£º&qû\Ç5Á:¸\Æ~K®,Âª²\ÎgŠ\ãÁ‚\ê•2}?Eh\\\æ 8\ã€d˜\á\Ä6Ìª\ØRG©[Á}fR¼6­œºH½JÀt#ò\Z2d\æ0xÑ›ş\ÅO™\Õ|¥\ÕıÔ¥ \åxG ª•#{½ò¶7}+IÅ»j½\ê¡İ¡\íPZ@”_Ùµ…\çü\Å1£\Ï+T,\×O°=×´\\\ç):—p¹·z3/ı2ts\ï‡Ÿõóğ&w3´\í”.œ=\Åå¢‚FUW\Üò¬ªZüXV–‘©‘@caª#\Íº\'jª…rj&‘ı\Ì7›9\ÂB[\Í&\r\n¯ş¼h\éÉ¡‹Ò©ûn5Ş€f\í\"õqÌ£~px‚}O(\Î\Ïõ\Ù!6wtŸt¢·Œºpú\ïXW9—´ñ‡¿\ã\ïO0\ï\Ú÷ÿ\È\ßoœªuq¼I\ëA98D\Ê\ÅSu°…±\á3V\Üy”\ß\Ù\è\ámJ}[e\ã=¢erÿ\Ä\êXE\å\r–\'q\Ãh|­¼©naÍŒAÀñmz…´ec¸¡6l£\ÈB\ÎM-³<@\Ùtñ‹°~j9\à@HŸÃ™ƒvU\n\0œZG¬zxW\Şú†WÚºU™\Î~Z®øpƒQ6¬²“2š/œ˜\æ\Ñ~\Õò€…\ÓUv‹uGi]\Ór98Ğº\Èô\åÕ£ûıcñºÁ9\àø) Dyû[\Şº ‘m\Åù4\îŸ3\'Z>>\ÒÆ¶y™€\n*iÌªqÚ“®\",Ó£\Î&´\é1µ^+T¦\ïœº¬œ»IÆ“	\ÓE/\Ê.\Üy»7ò\ç\ßôÎŸõ\æŞ¥\Ô\Z€Ö’´Š¯1£\ÕĞŒx\áƒ÷¼¹\ã¼¹¼*\ëA\ÉCŒõ¢\Û\ãMª|(}b~…[\î`¾IñrÊ«x\'q=(\Î\ÅT®X\Ú1\áøÎŸ1”\ç\ß;b¼œAEö.ºS\\7d%ö:Âª’§qªsE9E‹€L3<F\Çşl3¼\Ê\à”­`¨rz5?•^¡>\Ş;°qö¬|¹\àÁ\"“\á<\Æ~ğm”\ã\Ê\ï¬iC†‚Š‹k\ë…¦9\È8\âª‚D·#kP=ß©5fgo‚e\æ .;r„—JgÙ™5ûJqÜ\è>@\ç2ó\ÂÏ©µ\Û\ã\Í\Ú\ËZ•\ê8#\ŞË•\Öw\nª¼6¬WöVk+\Ç\Z¢Ö¿‚²:7\ìWµøù¹Qı1‡ W–À¬é¬œ<…6rIwl¿\åog\îom—B^·F_\çSz{­W\Úğª7»\á2¡³ñøş;;¡rşV+Ä†u5ÕƒV\Ù\Å;\ï }jûg§60i”mµ/ì”£Ê™+²\Å/J§\Û\ÄSÜ˜™—Ÿ•}=*=UxV\Ç\Öüö\Zª·sk©\ï¨J\ß	Š\ã3ÕƒV•\"`ÿpf´ô&šı\ÌA9e”\Ñú¥³\æ\Üil©pdvj¥\ÊÉ›\ä\ÉF*CdÇ¹\ã|&Ÿü;¯´şeª\ì9¶\çÒ›¯¯1ƒsú\è½2\ßySYj“Œ€e:¾V\ZŸw\Âñ\á\\mª‚‚\ÎZ\æı¶a#«\Ê\Ş.ùVvl\ßÉ¿ROµ)sP\ï; \røû‡ee\Éi~ñY\ã5Öƒ1]o\ßIÊ•¿EŒL\Ñt\\­4dO\ê8L-¿=›µg»\è>Œ\Ğ	®¸ƒ¿P…AHX/W\å15æ€Š-­{É›%ú¢\ã<ıòó\Æk5\Üß¥\ên 8™©•6°‚Y;=X%q’™‚ƒ\ÊHif3Ç¶a‘!$3g\ä\èA`\Ç3÷\ÓöL³6€~12huÿ;^u\ß2©­yf\Ã+\Ækl2ô\ÅC/	eP\åi\ìëª€—\"8ğ¹vne\Z+§\Ï\Ã½>(ÿ˜µš\ê-ğ>8ÿöHx}4Ì¬w+|\çO\á\á•\í\ï÷\Å*‹:ÿÑŸ<\â-|ò³œV9¸\Ûx\ã\ßqAENch\àb\İHÓ±´\ÒT\æ\0gnVUP\ĞYd6\nÇc8¿ÍY\è}qµ½\ÎJ+2s¨ÊŒ IñØ³•¯C7k37ğo\à‡Wf×¹3\ï¯k’!s‹:±ªü±,3(ûÎ“ÀıKPÿ\Ğø`¬ƒ3ò‹:.¡¶l\ÖL’qŒ×…Mg\è\âÑ2Ayø¬X•!¨``\âN0Tn®\ègO9Í©#?ş¡ñ\Ú\Æ\êX48´RŒL\Ç\ÑNC¶Š\Æ\Õ^òøl‚›‰3e&m\æWnú©‚r¢™\Ä\ÕÕ·ñ/@C‡¥šx)\"vC¥\æJ\î¬)«\Øe¼¾I†H9¯P8@<«\Ö7g\nx¨Cº´bP6š	Ç›…e\æuŒA0\ÆL˜\ÅP¦(W€ñ9‰1¾a*³“¬zü1\nÿ\İ\à´9ü\ão¯qœ\á‰*|Z«pL\Óq,…!“ñƒXN\åÌ@8Ÿ)‘y <|«.«6“µ´\ÃP™E\Åná—™Jo¾\â•w¼\í\Íğ®q¶\í\ÇQzû5>.\ã:MZ\éM\Æ\ëgH³\á´A‹Ÿ¢\Ê\ÌNC†l‡~¾dokbùR¼\ëd\ê¸p\ÜW(\Ó\Æ\çN\ĞĞ˜ƒx¤˜\Î\Ğ\"£®~’\å\'2\Ï\ëü¬¡¼ÿñ³išñÿJ\Û\Ï_WNS¬\İ^Yu\ßvoö—¼\Ò/³ò\É\Ök—aÿúñ\àøL\ëÍ<j}ş\r\Û~ğw\é«÷¯µ\É0`\'Z´\ìA0\È\ÆCT\×\àøI™–uZpğ_±&óƒ‚</ö3‡hŸ_µ\Ğql:˜¼†ô\Ï/_ª!˜ÿ\'a*«SŒ³†\Ó\'D‹=uœµ²g›7³\î%of=™T~p\é†}\Å¢\à_£ü\í#Šå³´.\Ö/oYÏ¿‹`Z/¤ø™xü¾\ÄÆµ^e\Ó\ÚD…srpJ*OW\ÚyË†ğñÇ¬£\ë‰\ã^hi\ã:\ãö³¿Yg¼\Ş&ƒ\Ã*\'¿²*\Ñ\ÏÂ\æğYŸd\Ğ9]Y-²?$0l˜X\Ë\Z5œ9Œ?w1H^C„e¼Ÿd\Æ6¦²:\Åğ³ó\\©\É\æN÷*»·²cÂ¦×¾(õEo\Zorø5)9\Ö\î\Íb[´²vòöª,Uşµ\Z\ës¹†\å\â3\íwÿoú©Ç¼\áWrŠF€•*€-\ãw)f×½À­~¸ü\èş(ø\Ññ\à¸ø_µ\r\Ë\Ï\ÚC¾CA\ç½¤edB´ni\ë›\r\Û\Ûf™ƒ\Ğ4†\â¼;i!¯q€¤s\á QƒÌ€Õ‚\ÑÒ›$¯\áüò-µ“o\Üğûp\0´Ô¤Ur*¼\å‡>3xf\Óz\ß	 øEg8Kõ\È^Ş®zt¯\àC»ırBJe\â‡h\æC)\ã˜y\ág|¯ô\Êo\ÏñŠ@ÀgÜ¯T>.:>\Órœ_…º[\à*e&%\ÊRp\r¢\×¿…\ë.\ï\İ*§¼w‡ñºG\r]_Œˆw\Ä\çÁ»ñUgT~‘„”\ÎŠnD+~ v©Œ\Çü\Ì ’IHE¦\å\\cE*\ÊFE\Å~™¬“o^\å¥\ä¨\È\ä¤\Ğò®\Í\\\ág\ŞxMğ¾w„#¬ıµ\Ğ5”1l^\ÏN\Ë\Ë)s@°˜¡”[/\'UOQPø‚‚rö\Ö\é\è·?÷fs\\}\ëU¼\äó%+m{;|=\Ôu¢ \ÂLY…^N\Ö\'QCÀ@Àù S€C H€³ü3U7\Î5~\â<m8v\Ì£°x\r\Z\Ñ†7yl€–9\Ùd¨”Š;5sù»GE%Vv\â;?W|\Ò\ÊÁ]\Ôb¦¾õ\ËşüõÁy]8Ê¦uÔŠ¾@iü¯½2şöN/+É\î÷†X\É78pbQ\Ùc™Lgei\ì§\ßd\Ó?{\Ì|<	†ó\Âù\Íşf\r7\æ¡ûƒñ\\Ù­oñ¼\Ù\Íü\ë†\0«—_ˆ2]ÿ¬¦\Ş.T\Î6­\×Í†§6z\æÀš\Â2sÀû2(b /)r\Â)‹~„ˆ¾aˆ\ãH\ãN\rÜ²¡EDKG:»\åMoúõ_‘½À\n\æ @Š>võ uˆ‘n£1»öof\r­GZ=¼G”¥•\×À\'x3ÿøWl8±\n¢ŠÛ¡\×ş\ì¼4Ÿñ[şyR†\Ä\İZ^¡óE÷X¿n\èvT)\Ğ\ê\å!›\îAVC}V\çcZ\Ş\íÿb\Ç\'ßª`˜Æ¡1‡¢\ŞÖ•ˆŒ€n®ò‰‰Š\à…—Ÿ–\êX“l\â¹ÿT^8Á‰ƒ\Ş\ì\Ûkù—Œt\å\å\ÇöóO\Ô\ã3œOJÔ’úºõPYq6öƒ‡¨R‹V\×LTr;V–Æ¢²„\ç¡òDÿgi:F“\áü9¨\ë\Â\Ëd\0\à`¹nedXZ¦û\ÇğUş^\Ì\Z`hHı1ºOƒXô\Æ8\ÌÌ—°\\®\çgp<SÁ\í08;*¤\ÊÉ¤O§9`½Š{j£q` ™Ö²·K\Ñ\İÀOï›µU†€lº\Î\ã§6+\ÅÀl’ß ÁU/Nq\æ\0g\Äßƒ›Vn‡!ª\éŞ¼\ì\Ô.C’•¶¾Í™¿\ä\Ôb…>ô 9¥hµs+™\Î\Ê\Ò™D”õ\ÌoH&Ñš\åÅ¨\åhğ§,)w³\È98,õ\è,¾£\ã\è†\ïJ˜lø/¿\ËÕ·\ãø\ÜÔ«\Ïû†¾t\åğn^^Úµ™÷õu¦_£õ^\Ò\í\×\ÊSŸ\ç\Ş=\ÈœP9mĞ‚wó\ß\å_ç™\ëC\ç«Pw«|p§W\Ú.‚-xú\ÍWC\ë”ñ#0Zy³[\Ş2\Şg\Íu+:£Ÿ…nD·>Bª\àÿPá‘‘–¶¾\åM¿Bù•\çXg~³\Ç*Göy³pš_\Úò¯?û\Ö\Z=VT~\ê[\ë\å\é:ù÷Ë–[\ëó·Mc!¦>ª˜/x\äÁUt¼ûc\ÏZŞ»=|\Şx•\çóS\n\âÙ·(H\Ò5\Ãú¸¦j=<½ˆ–‡wKL÷\ÆY~\ë\ë\ÕA˜v\Ù\äó?§\ÊIN€ŠJZ9´›2€¤JL™oúñü\ïxx|\êeZ¶\æE@À¬\æ\Ó6zyº–\Şz/h¡\Ã*œºsx\ì\áoûß•0®ÎŸ³šÏxı\ëôk~zù\å\İ[ùú`>‚…^´\éş8\Ëo}ş…N6„ô+¿0´vS¯<\Ë\Ï\ìE¥¦\0°{‹7ƒJ€JOË¡˜\å*˜`9¾; —\ç\Û\á=\ÒùDŒ[öf]™D˜§z\Ì|>\ÒJ\Û\Şô¯¿BÁU±~½J;8h›C»8\È\âzòúZY\Ïş\ÌxŸœå³¾V¼\í¸\\lv3u\r\ì†?~¥–m\ê\åg8=FÁ\ã6­ó¦^zÆ›~ùYV,W<C]\n¬UË¹¯.\Ë\Ó\Ë@f*œ:‹V¼n:\íüQ\á¼ù\â\Ò6\ê>$\\/ÿº¾½FtC´ò\Ğmsƒ“\Å‡\î\ì\ç/µ¡\ëWLiÓ¯ıŠ¿(\Ä|`\'UxJQÁ¹rK0ZJ|w\0Œ\í£e\Âf_{.¶eNcÖ¢Ù¤ş˜C#_}`ecp\ĞY\Ş\å\ÏtÍ’®–\ãza]¼‚]Ù·=T\Ö\ì\æ7÷\ËYv\ë3\Ít–n\Ü×¥T_‚B­>^h\Âw)¿ø4U\î§µŒ~6mÃe\ÓúP™l”:‹–]u\'TK\ß\Èp\â<\ì;¿ó€c&:ñ\ÌS\ç&\rc\rrxtÒ®Y„ºnÜµˆ”7ò£GŒ÷\ÌY6sÁ!‡M½ò<U\Âİ”\â¢b†-\àÔ‹¿¤V\ÌBgÖ¼ ¶ÛºZAj#\åÿ\äaù>9™\Ô(C\Ù	-x©\×\r\ß\Â4^7\êZ@E—\Â|LŠ7,\åQ6½gÎ²›mğ?\Ã+S%DEô\í\àN¯L_BÅüõ/2)\Òdl¯—Å€&&ùcd¬™Y¨23\'fS-~e\Z\ã%´\èúùE\Ïß¾´¹^º\â{¸~\Ñòf6¼j¼\Î\ì\Í‡Œ†AG´t\\¡u¥.¾m+Iµ\åò-\å‘\"aLH8™rF\åtv\Ü65Œ9 0(¾ \ßz­\ì£s3]?Ò¸\ë“Ê»6\Ê\Û\Å\×/zÿœÙ›l\ìÿƒ¨xÔ¢·ZK\Û\ÃûT_)¶\ì‰Ì–•§1g™X¨ú®g\ç_„–÷m\İ?g\Ù\ÌK\Ã#²\Êj•Pñ\Ú`ª\Õ\Ã/±“É–Y9u^ö¶ÍŒoÿ©k‰_r2s+l\â™ÿ\ë\ï\×Y6sÁÁ\Òğ&#›µÅ´?pÁ\ë\í¢/Zd¶¢™L9q³¬Ê2¾È£\Îi\äG›Ï»F\âÒ¿s\ï>ä±¾\Ú\ï\İ\á-ü\Û\Û=§N:\rô\ïÿ®3X€\\À™\Å\0\0\0\0IEND®B`‚','image/png',NULL),(3,NULL,'\0','Japan 2',0,2000,'japan 2','japan 2','‰PNG\r\n\Z\n\0\0\0\rIHDR\0\0\0\0\0±\0\0\0\ÉOx{\0\0\0sRGB\0®\Î\é\0\0\0gAMA\0\0±üa\0\0\0	pHYs\0\0\Ã\0\0\Ã\Ço¨d\0\0 \ØIDATx^\í][$U\Õ\í?5w\å:\ÌM_hE|t\0#/¾$~‰†\Ûp11òE}1ú	‰ú\"0WçŠ \Â\Ì0:\Ì\à„QÀ[µN×®9ufª}\ê\Ö\İ\Õû$+«Wu½«ºk\ï\Ú\çTuõdÍš5Óµk\×N}\à…Á`0„(’ƒdÓ¦M›†¶\ÊÁ`0PXr0.9¬\ËÅºu\ëflÚ´\é•×¥\ÊAš6m\Úô¬rŒalllœ³%ccc\Ê¼p\Èš6m\Ú4ôDJ\Æ0mÚ´i\èK•ƒÁ`0x°\ä`0(J\Éaıúõ¥7M›6½º\Ú*ƒÁ@á’ƒdccccaK\Æ\ÆÆ”\'ëƒ…¦M›6\rm•ƒ±±1\å	^CKƒ¢”6x¯M›6½\Ú\Ú*ƒÁ@\á’Ã†\rœ0666%‡¥7M›6½òz‚\ÅÂŒM›6m\ZÚ†\Æ\ÆÆ”\'xa0!.%‡<c˜6m\Ú4t©r\Ø\è½6m\ÚôjkV\n—6n\Üè„±±±±ğ,9d(½iÚ´\é•\×V9S¶\ä`llLy‚ùBÓ¦M›†GÃ¸OA³^_ª\Ãh°f\Í\Zw4{OK\Ã\ä€\ç@²÷´p\ÉaS.6m\Ú4cÓ£\×[¶liõ¾\é\Å\Ö~\å\Ğ\Ô^99¯?ÿüó\Ó[¾ø\ÅB\å+_)\Şßš%†cÇ\Ñ~Æ‹Ï˜/p\É!¯´ıB%\É\Æ+\Ãş>ø`úÀLw\î\Ü9E»\å–[\\’Àr4\ã\Åc T9(û…lÃŠ\ÕO>ù¤K\0h’Î;\ç\r•E—şL§‹Ê¡\í°ò7M¯†¾ñ†Š¤6,ß‰a†·~\×şM÷§Ø’\ØûZ=‘…’9L¯¾ñ\Æ§\Ï>ûl‘$À\Ğ^ta\ßô|4^É¼¯Õ—*\Ã\Êa\ëÖ­\Ó_şò—.1Hûş÷¿O\×5,$9\àR&{_V¬°Æ¤$\æ\î½÷^—01y\êÔ©\é~ğƒNì›¦É¡=—>‘/0^\Æ\Ğ\áb6Œ@õp[~µ\Ë\åÊ…Öñ\â1\Û\r+ò\ä \íriX!M_?ø\àƒ.	\Ä\Z’D—şL§¥r\ä¾¯Õ³\Ê\áŸ˜½i¼2Œ\àwW%2Şµk—K·\İv[1\Ä\Ğ\Ú1^<Š	ÉŠõ\êØ†+\Ìo¿ı¶»\ßA†\Ûò	J\\±\Ğô7^L\îlX\ÈÆ«\Ç_úÒ—\Ü¤)óøñ\ã\ÓmÛ¶©ú/&#°\×æ•ƒfı\ÉÁe\ïMÓ«£eXdÑ…=\Óó\ÕÅœƒ—šØ›¸\ŞBÓ«§1×€{\ßôri<â­¨\ÈûZmÃŠg\Üô„¹´\×^{múõ»\îRõ3^\\.%\Åú1v•\Ã\'!ŒGÍ˜GÀ•T	²ü¾ü\ÊDØ¶c\Î!\ï‡>’0˜]\ã\Åcüğj\íÚµE\å \íò¥a…·\Ğô¸4ƒThnn![ş\ÔSO\åKÊ½+]m\é~µ«²\ä€;$\ÙûZ]®>ù\É\ÒJ¦Ç¡Ÿ\Ë\Ûo·g\Õ–\ß}÷\İù’r“şaò¸ıöÛ©}Ó‹¥¥r\ä\ĞÔ\r+V€Ã†\á–#I„\r†ô{\á…ò¥³\Í\ì/\É!ƒfıOğ\Ãxœ\Ì\0&oÍ†lX{n½õV7\Ï ÷?H“Ä¡ñk<?Æ¥L$Tšõc\\$—1¼7MC³\äĞ¦UùÛ¾}{òö™\î^#9È°¢½‰[\à-4½<ú3ŸùŒH”û¸\Â¾\Æ<A—-´/@B%‚mb\ï›N—*ò¾VÛ°b‰w6\ây&¸«	d½¾*Ÿ‘ 0\äø\á\èØ¯ Œ‡\ç\î†x‘\ã\n\ïµ\é\Å\×Os÷\ßw_\é}¬\ße\åpñ\â\Å\Ë\ì#\à¡1~ƒOy?\\\ßtÿ\Ú%‡,1\à‡W\ì}­.%\ÃrM(b™¿‚·«†e…¶Qµ„M’ƒa>\äP\ZV4€K’1Œ—‹\Ã\ÊA\Z†\Z\\Y\ï¼wT›†aƒø\Ç\rÿ\Æ*¿\á=¶½\Æ\Ã0şD×¯´ıB¶\ä°ÄŒaD¬!p\åş\Üs\Ï\åKÛµ{\î¾\Û\Ù\ÛUó)\íö÷Ã%¼¸\âŠ+f36½<ú³Ÿılñ†‡\Å\îŞ½;W\í\Z*‚\ãÁüB\ØP¥h·\ßt?\Z·O»\ä%	L,Ö­\Ó\å\ÊAV2½4Z\îbDu€·°\Ş\ÌÔ¤UÙ€ob\à–\ì.÷\Ïtº.*‡Œñ\\†¦öŠ\ÊÁxy\Ã\á¡®``;vl\ß\î*‹ª\í4†Q9`H!?\Ù\ÖöÙ’Ãˆx\ÉƒvûŒ‡\á\î’^\ä¸\Ò{mzù4.5Î£\İÿıt{L\ÏG»\ä%—ò9ÿ}­¾”$c˜^J=ªA\Z\æ#º\Ş\Ó\Íu©r\ä\ĞÀK’1Œ——Ÿ~ú\é<T\ç\Óp\åD³\Æı3~²\íW\Ú~!Û°b$ú{\î\É\Ãt¨\á³ºn¸<Šá„´p{L\ÏOw6¬À‚+¯¼röfÆ¦—S\ïØ±#\Ói¡ÏŸ?\ïô‰\'Z\r;`\ÇO>/¾ø¢ó\Çw8\rûm·\ßtw\Z·OKrÀ}u\ë\ÇtyX!+™^J Eı\å/\Ù]jDƒ\Æ\í\Ï\Òb÷DH“Ä‚vG–X$¹`ù§²\Ä{’PE¸\í\èxL7\Ó\ÑaE¢½¢r\ßôùÏ»q8~0FŞ¿o\ßtÿşı%>yò\äô/ùKiù\Ë/¿<=w\îœÓ‡¾s\á‚\ÓGq\Í\ÚG}4=|\è\Ğôoû\Ûô\ì\ë¯;;\'³\ê\0‰\ìûû÷¿ÿ\í\ìú\Û!¬Ùe\åk¯¹FuÎƒıa…Tš~!zXq\Ã\r7L_y\å•ü·f­}Û—%=Ùº8>»\Ö\0\îk\Ä\0 Š»\ëú‡z\â\ä¯’\×#ÒŸşô§§È¿ZkÖš7TJ\×_}\Òñ7¤\Æ\èú‰·Q\ËkT©öF;¬ğyó\æ\ÍnŒ²x\Şøğ\Ã;ÁşóŸüN?şøã…\ß0\äh´ı\ë_ôs\Zo¾ù\æôsŸûœ\êx›\ãl\ï\'&(5ıCv•ƒdŒ13&\ãÎœ93w °»h~r`~æ®ö\Ío¸ÁŠù\Z\Z?şñU\ÇÙ¼\É@*aT\Úş>_\ZVxÇ¨-9ôUI\ìøZ-Õ‚Ÿ\\å¿®_¥\'X ¯º\ê*÷zŒº\Ï\äğ‡?üA\r\\	\è¢ı÷¿ÿ\Í_;9ü\ãÿ Ÿ#J¶]]À%‡Ç¾4BQ9l\Ø\à^•C¢=Vt€.\ä‡òpmU*‡”†_ˆ²\í\ê‹>¬ÀıERˆ0~/¡µU’1FÌ–ú\Çè“ƒ\â8›\ãj„T\n1Æ„¥\ÖØ†\Şx\ãdüù\Ï\Î\Ï\áš%‡\Ë\Û`É_óÖ®rdÁVˆ.’\é\Ïô\Ä-…òz„Z›\æ\èMÚ¢\Ï9ø\Ék‘\Û_ÿúWº/!Š\ä \èøøl«‹\ÊA \ÉÁ\Ó.9Dú3mÃŠ\0Ë’½rmrPoCs?ÃŠ\ì\Å\Õ#gKıc\ì\ÉAsœÍ‹q‹4n•F\"ˆ1nl\Ò\Ú—†²pLZ\Z\î´\Ó`Yj4l+À\îTœd›\Ğ0ôYt`[\ÙqÀ \r?¼\ê\êø\ìJ£r$\àU\n¡–\äÀú3=ú\ÊÁšµ®\Ûw‚\ãl\Ş\Ü[\å ]yQx÷®]\î¦5,7\È\à\ç\ÇrV3R;5\åX:tğ \îøÛ½[u<wÁ½$¼¸ú\ê«İ‚eaü\0fŞ—Í¬Y«j§OŸV\Ï]°K7ºDc\\\Ô\ÚÏ’C—1\ä\Í%\Ğ\ßşö·ó¯Ášµ\Åkw\Şyg§\Ç{.%‡®bt‘H¦—²r\Æ‡¬Y[´†+cš\ã·+FĞ»DT\n!\ãF)=\á¥N_ø\ÂŠ\ßı[³¶\r?şºö\ÚkU\Ço\ì®R\Ô$…gpUB]ğÄ½\È\Å5òz‰ôC=”-Ö¬Í¿á„S\ß6\ZŒğ~üúR«q©²\Î~\'•C\êDG\×lÕƒµEhgÏU¯]p‘\Â\Ê@\ÉHu~\\\å £	#1¸±L°|HöoP±fm^\r3\Ö¯]0bN*¦\ìn|\n\ìú\Ü:9lÚ´\É9\Ã}Ûšõû`ü¬W_}\Õ`á±§9^\Û2\Î\êš\à¯c\ÌWTù™\àE±ğšk\Ük­\Æ\Ä\Èd©ıû\Òø\ïƒahÇ®µ)\ä\Ä\\}]\åo•CÈ¸\Ò`š\ã±k\Æ\\pK·\à*?³\ÊA2FÆ¸\ÅU\r\Êõ‡`kÖ†jš\ã±kÆ‰]T-\Ø]µˆø±\ä`\ÍZ‹¦9»\æ.“C•Ÿ‰{‘üÿ_ªÆ¼ƒ$‡&ı»\Öo½õVşµ-Nc?\ä14Ã¢µ—^z)\éø\ìB\ã\na\ä‚\ZW\åot•\Ãÿ|\ë[ù\×f\ÍZÿ\í\ëwİ¥:.»d\ÌÁŞ‚ñ{Œ*?\î\'Û’1Rˆ‰$d 8’ñ‹¦ŸŒŸ\×\ZC@s<öÁˆ7\ä-€K¢U~&p{3Æ¸”Âœ~¡µ\×5³KNC\Ğ}0N\Î8)³\Ô@÷¬\è³ ¦Q-„B`£±ñ¬ÿš}‰C`\Ç\ßP\Z•:‹¿:\à\æ\'}—´Ã”AQg¯/f_¢Á\Ğ4\ÇcŸ\ì~¾@\â/†”¸t\ÉAJüÔ´Ša8tVl¸\Æn—ü\ìOJ|ªDü\Ì÷¾7}\æ™gjù\Ì\é\ÓÔ†)~põ…\Ù\Ğ \ÅO\n\ïÛ·ú\Ó\0ÿ}ùóŸÿ|4~ğ´ò›nºI}|öÁdT,H ©ñè’ƒd“ª•1y:¬ƒ\Æn\×|ñƒòy\äfmUı¤ğ‰\'r\ë\ÍÚ={V\ÒOßŒ$ÁN\à¸/B\Ó?d—\0ˆ\ÍÁ›¾\Æ$dX¾h4\æ3˜½¾4¾\è‹/º3\Ç\ßÿşw°\î\ï¿?ı\ãÿXk_t?x\î¥\ïg‘ö\'E#`?e»°\ÖG¿¯~õ«•öEÿovf\ï\ÂO\İş ‚b†\Ğ~!ñ(Cª½\"9 2…>#¸1¹XW¶\Ä\à\Ïg0û}ñŞ½{\İC>O:U\ËO>ù¤\Ún\ÈCùy\â‰\'ñ“\Êø\Ã\Ívı\êW¿RÙ‹ñ\Øüô\Å,N›\ïgÃŠ¸\ßA\Îò.ƒ\ä\ì_2iÊ¨:B»}3‚_¤O!˜‚şZ\Ê’³\É\à’CĞ¿/F0±m`\ÒØ‹ñ\Øüô\Å,şp‚\Öö÷¹H—\É2‡¬$\Ù(tªe—t<{¡ı>ôc=\æ\ç§ÁY€7õ÷ø\ãS›\ÎO\Ğ_«Sü a¥\Úoªñ?lB9rDe/¦“ı$\Ú=\Ôşô¥Yü¹¸n`¯H˜\È(½\é1Œ\ãıĞ©†q%$f·OÆ¸Oˆú\ç?ÿY\ËcB)\è¯\å±ùIe\Íö€ñ ½\ÍO_\Ì\â°u\å€aE\Õ\ÊòĞ©†cöúf¾Hÿ²SL·\r\Ú\Ğ^L·õ3\Äş¤2ó\Ïô±c\ÇTöbÚ‹\é.üøöbº­Ÿ¾˜\Åa\ã\ä\0R`\âolŞ¼ù2öG‘\Â1{}3_\âG}T\Ë?ü°\Ún\Ècó“Êš\íG0)\ì\Åxl~úb‡r\â\×ô÷y–2`^A²FY9C)9:f¯o`\Â©\Ö\rûku’	Z¯¿Vµ?©šùg(“\×_«™M\ç‡ô\×jf“¡\íşô¥\Ãø£rˆ­_¥]rÀ‰Ò›„—±rÀ}\Z´=£3›\Ë\à\'•™†¶gZf“aYüô\Å,[UU\Ã	\áeLrCK·\r\Ú1ùIe\Íö€=ª²\ã±ù\é‹Y\â>mŸ\'0\à.cd®\Ã\Â\ãL=°~\n\×\Ù\í‹ ø\"5xÁô\×ò\Øü¤2óÏ€	<½3›\Ë\â§/fqˆûŒ´ı}¾”²LQz3Ğ¨JN³Œ¤\Ñ1{}kœiq{«.˜\ZúC\Ğ2›¥ Mô7\Ôş¤j\æŸgZ½˜f6Š M´/š\Ùdh»?}i.94°7\ê\Ê_¢ÿÔ˜n{F\í\Åt[?C\ìO*3ÿL·=Ó†öbº?¾½˜^\Ä\Êq\Ì\â°}\å@\Şôy“\Ãûï¿¯BÛ e6–ÁO*3ÿG\Ã3m\"3›mƒ–\Ùdh»?}p›\ä€yGü¾\n“—\Â€Q¬p\İu\×\ÍXi”x\ß%‡|hQ@¡\Ñ—Hcöû\Òö¥2<ú\È#—õ\×\êG²¾\Ì&Ã£>zY­jR5ó\Ï\à‚¶…?f“A’C\Ø_«™M†¶ûÓ‡Fœ…ñFr`\ëû\Z	!Œ\ß\"9Hˆv²u$	R´Ÿ¹Bû}iœiñD\ï½÷^-ûgZV£oŠŸ°¿Vµ?©Z³=`9Ó†ıµ:\ÕOª}Ñ©~\ÂşóÔˆ\ã¦ñ‡JAÖ—ş³\ä\Ù=.e”†ŒK)1û>\ã~Ü°!U\n\Z%¦¿\Ï|‘\Zgt…İ\Ç\æ\'•™L\n{1f6–\ÅO\\T\r\â\ÅùÜ“²²Ôºu\ë¦kÖ¬‰bıúõ.Y¸YUb\'dÓ»ï¾«BÛ e6–ÁO*3ÿmƒ‰\ÙdX?}p÷\É!{!—1¶`\å3\í._fË˜mµoI?\na‰ \nk×®u6±\Í\áöú\ZÁ„g(j ÁTe/¦1\çÀl28?A­jR5óÏ€`\ÒØ‹if“¡ğ“h_4³\É\ĞvúĞˆ	¨¾\ëú#9„ñZª¤:€e%t(œµ\Ğb\0ü I`,…\Âp\ìZ•k\ÜÀ\ÌP\n&oÿ5\ZA\Ël2„gôMü¤\Øoª™<ÿ ?f“~š\Ø\Íl2´İŸ>´K$şouı‹\ä\àõ¿T9d+¸•=v+g\èŠa·IµPü•8ó·{÷nú¥2”\Îè‰Œ¾\Ì&\Ã2øIeæŸ¡¦ ¿–™M†\Ë\Î\è‰\Ìl2´İŸ>¸T9\\\×\'Ô°ß \Ã\n€x[\È\İ`¾?$<6\\Ldÿ5\Z}™M†R\Ğ&ú\Ã\åIf“¡\Íş¤j\æŸ\á²`Jô\Çl2øgô*{1\Íl2´İŸ>tlX®\ëO‡x£XaË–-3Îr\ïh¡ñÇ,°»À\Ú\î\Ãğü!9ü\éOš¾ó\Î;µŒ`bû¯\Ñ\è›\ì\Ç\ë¯Õü$\Øoª5\ÛvÁ\Ô\Â_’\Ò_«“ü4°ß§\ÆU=9ş¼x`\ëû\ZCò0~]rğ,+€öW”Œ\ÒD÷U1øÀ\Õ\ß?’¾H\rL’AZ¾\Ì&ƒŸRı¥úIµ\ßT3ÿ¥ õúk5³\É\ÑZ\Íl2T\í\Î\ÊrYób\î˜\Ì÷ \Öğ~\Ó\í«\Ò.Àó\ã?ŒÇºş2¬ ¿KE\Æ\è‰7l\Ø@ºk\à’¨øEr¸p\á‚\nE\ĞJ&M`ôe6–ÁO*3ÿxZ³\Æ^Œ™M†*?\\%“À\rS/³\É\à>—ò`&¿õ5—\äÖ¯w(7 Š³ı	¹¨‚ı\×õ/U9Ï’ƒ@\Ş\ìPQ5ü\êA’\Ãùó\çkù²`(´m’¯¿V7ò“`¿©\Öl¸´^­NòôG€•’w|†Z\ë\çĞ¡Cn}—²@gÇ£¸\ê&“\êU\Û\ç\ïO¸‚^†\Å\Â;\Î>”¾ sHo¿ı¶\n\åÁ„r’LÎ„û\ã.“fÉœ­Ù‡‹@d6\Äú¥òP~R™ùg@\Ğj\ìÅ˜\Ùdğı\à{bB3›H]\Û8±C²]uŸUH\ÓşñÊ\ëŠ\ë\î|\ì\Z.g~%9œ;w®–ñ»\íşËY$h5~$h%cË—£\Ñ)~¤rH±\ßTk¶\ìm)~°>;\Ø5¬õsğÀzüµ.õ³\íªû|À\Ú\äÀúó\ä€=‚}\0}Bn³FrÀ©\Ö\r·[$f“Á%‡\ì‹p_J\"#\à™M†6~R™ùg(’C\Ğ_\Ë\Ì&üz0›zJ@‘ <h>\'—‚~‚ºşnHô¹”$ct¨Q&±\ïn\Ş!ó€\Ç\Éj\à’Cƒı§ø)­dn¥Fr`6JÃŠ¼_šùg8|ø°\Ê^L3›ğSõ}\Õif“¡\Ï\ä\0„	ó&¨T19\é˜\èRRö\ëT}¾—%Ô¬¯•\Ã<’&x\à[‚o&ˆ\é6•ú†öb\ZA‹/¢	\ĞW»?mü¤‚ùgZ’CS„öbZ&\n›\"´\Ó}\'9»‚$‡X\Õ1º\ä\0À7‚ö­·\ŞR¡mr`6ü •Œ­Õ¨˜MW9ıû\Ò\Ì?C˜Rı1›¤ß“\Ì&C\ß\ÉÀ-\0l›\0†ÿy†Ÿoõ°¢Ì«rÀ‡úÀ¸/zZ\Ç=ô\İ~\r$9hü\à\Ï}ı/d\ëÖ­j€Wû‘$”`¿©\Öl\Ø%‡ş´~\Ú&­Ÿıû÷\Ó\ã¯k¸+d;S\á*‡Š\Ïwğ\Ê`;<ğE¾ñ\Æbºmr\í\Åt›r}µû\Ó\ÆO*˜¦Q\î³şZ„öbºmr\í\ÅôP\ÉA®¾\Õ÷[`]Ì¹˜˜\ÇI\Ëğ^\ëa…»Õ³c=ô¥L’ƒÿ\ÅVA’C“ıC_f“•&‚P\â\á\Ë(İ©—w\èabI2ú6\É\ì#\à™M©Püş}i\æŸ•C\Ì&ƒŸR¿OL\Â1›C%™`g\Û­½ûXq\à&\"\É\ç‹\ã1´\ß{\å\Ğ\å\")@røı\ï¯Âƒ>H·]$f“Á\İOAl„(Æ‡Ş—‡\äÀl2\Ã¯_\Ìü3¸\ÊAa/\Æ\Ì&C›\ÊW˜M†¡’À¶\Õ\0­_$v|E+\É}ğ¼&%S’\ÖÅ‡­ÙŸS’Ã B©bT.³g\07I~ÿ¾4óÏ€ä ±\Ó\Ì&’ƒ\æóeŒ\ß;0›ƒ&‡|\ŞA¶(`\ëi¤‚d\à¾~\å \Ü[r@RÀ­\Ì\È\ÆM2\\[ \àÏ=;}ıõ\×k\ë¢<\Ó\ìW\ÈHZ?)\É@€H 9hı„ÃŠ>Y³=\à\"9(í†¬õƒ«UŸkcœ®õ3drÀ‰K¶ñ\Ä\ÖI…$\ÌE`¨û\Â\Ïc‚şÂ¶\ZY®\ÍQºşÌ™3*`]”[l\ê4\ÙdÀºaÿ*\ËK\0\Ïl2¸\ä÷ô¥™Ia­f6\Â\ä\àu\Z\Ç\0³É°o\ß>z\Üõl‰\Ò\å‰¶ª>N+‡y\Í/0¤&ù 4û\é3*f“!¥r\ÆÏŒM“C\ß\Ìü3\\6¬Hdf“¡m\åÀl2úb6öy•ƒCşpŠ&º\Ï\'=5şô\é\Ó*É¡Áş#\à™M†={ö\\Ö¿N\ãŠ‚\Ïl2`]ôÙ¶mÛŒ=h\æŸ\á\ĞÁƒ­ü1›’bŸg•\ÆĞ’\ÙdKrğ«‡ğó(\'‡˜ğØ˜}P˜ Á\Ã+À\ĞXaC¸ş\Ïl\Ğ\ê\Ô)°n8¬\ĞÉ\Ùdğ‡)À¸\Ïl2\Éa\00ÿ.9şZ0›˜`Ÿ¡8¾™M†±$\0ñ\Ë>Yr\È3†vX ¥ˆ<«\ËqPW@À¿ö\Úk*\àR&’Ÿ\ì|\Z€g6ğ¸ü:{L\ãVW<³\Éğøã»€\Ú.gŞœû\Ğ\Ì?’C=LŒ\á*\r.µ¡İ„d‚—j>Ï˜˜M†1%Lr²\Ï\Ã%\\YH\rpœi1.\éjö´kHrxõ\ÕWk\ÉŸAq$°$ŸbX¡°\ë3‚E’ƒÆ$	º>Y³=\àÒ°\"`Ì«\à°\Øşch¥õ\ã\'‡&¬õ3¦\ä W\ê\Â\Ï\Ãı©\r\ë Šö™yC/R\ÜC2g\n#90›R9h\ìúŒKNxf“aoP9ô\É\Ì?ƒ_9øŒ}\Ó|\Ì&’ƒ\Æ^Œ™M†1&‡ğó˜,â  9ü\îw¿SWüE2§F#90›M‡8«\"90›C+˜<9)\ì\'œköš\Ùdh3¬€f6^|ñEz\Ü-#\Âiù<&l\å1\0\Éá·¿ı­\nH\"\á\Ã\à\Òh<³É€u\ÃşZ€g6\\\å ‚QĞ—fşp\ç¢\ßC%ÿ\êöŸ\Ùd8 •C\Ğ_«™M†1%L\r¸_\æ\äóurø\Ío~£\ÖEÌ£H•ƒ¥1Àl2¸\Ê!\è¯e\Ì90›~rè›™I\è‡IF\í~3›(÷5öB\Æ\ÉClf“aLÉ±`\É!ƒ$†Z¸eMğ\Ì&~x\Ålh€‡½0›H\Ä!Àü3ø•\à\Íö±\n\Ì&ƒ\Ì9h3¦?o\Æl2Œ=9\0£N¯¼òŠ\nar Ü’ƒH\Î0¡Fr`6ü\ä³\Óø«f“a\Èaó\Ï \É÷ò³ı«\Ó\Ì&\Ãş r0\Í~\î\Ìl2XrXbHrxù\å—k9–\0”š˜\ÄUœñ<€\×úA€‡ıµ\ZÁ¯õ³w\ï^ˆ;¶oŸs\Î]jL(\â^\Íö€q\ç\"ú¸}\"ûW§µ~\\rPØ‹]e\Óú±\ä°\Ä@À\ã‹üõ¯] ¦«’€a†\ÌG”†\ÙA†\äÚ‹\é\"9xıµ\Z~´ûS+$˜h¹\Ê \Û\Çü3\ímÚ‹i©\Âş¾\Æ¿\ì{B{1ı\Â/\ĞşcÂ¨“¾Ä—^z©–\ë’C7\'‘\r9p&\Â]\ëG’ƒœÁRX’\ÆOX9tÅ˜HÄ¶øÛ¥\Ù0‚\Ö\ï—\Ê]ùArgß©@\ëÇ’\Ã/Rƒ\Ô\ä À-\×H\Ì&Ã£-‡\Ì&C\Ã\n!\Øö1ÿ¸Š²¿¡f6\ÜÕŠ\n{u÷õ0›––ø“\'Oª°k\×.jC\Ü]\Él2 9\à\0um\ÎZªƒ\ÙdpÃŠ, Ü‚6\Z‰!¶}\Ì?ƒ$‡°¿V3›E\núC\ã:>û}0›––xÿ\Ë<q\âDT·I\â§Ê¾hW9\ÈÁ*g6¥F\åÚ‹i\\ö\ÄÀò¢HÔ˜_¨\Ú>\æŸ\é0hS÷¿Î¾h¿B	\íi+Ú‹iKK-¾P\r\Ú&f“Á¯R¾\Ì&ƒósõ\ÕE‚h\n!\æŸ!¬RÁl2Tùa—.C0›––\Ú\ãÇ«\Ğ690›m‡\Ì&CQ¡\äŠA û\Ã\Æ}\è\Û\Ñ\Ì?C´uûjf“!¬PĞš\Ç2›––Çs3\Ê`\rp‰\n|ıõ\×S{X}Rı\àÀºù\æ›g­_\ß|\ÓM®OS?xphø;v¨\ÏR¨Ú®_ü\â\Åç¦\Ìô\ßwï½•v»òó\Ío~\Ó]™Àl`Ü¯R•~ö³Ÿ%}\ÆXÃ‹o|\ã\Ô\Ş0º\ä€3ùÑ£G!¥‚hã§¨ $Dc]fC\Ì=À\îJ,¿ B\ã\á¶U\Û\Çüi€›‡˜½˜f64xşù\ç\é÷³¡AªŸe\Âh“Ã‘#G’¹Ir\Ğ\Ø\rY‚¶†\n–ä ±²øAõ€€ÿTü\Z®\Û.\Æ.9T\Ø\í\ÊOj\Ğj\í†<\ê\ä€RkLØ¹s§+©ı/QP¥\Ñ}™M\ß³\Ó\ès\çwªƒ\ãk_ûZ\ãıñı`\îA‚_*„˜\Æ0Dş\Ï\0Œ\'5…\Zeu\Êöˆş¿Ÿü„Ú‹iø©²\Ó?úÑ\Ü¤Š¤\Øı\İ\ï~—\Ú&80ü3†iÓ¦MCOŠ…\Æ\Æ\Æ\ÆoŸş?\r\ÚÁ³\ÆÀP¤\0\0\0\0IEND®B`‚','image/png',NULL),(4,NULL,'\0','Japan N1',0,3000,'japan n1','japan n2','‰PNG\r\n\Z\n\0\0\0\rIHDR\0\0\0\0\0²\0\0\0O\Û\n\Õ\0\0\0sRGB\0®\Î\é\0\0\0gAMA\0\0±üa\0\0\0	pHYs\0\0\Ã\0\0\Ã\Ço¨d\0\0(\×IDATx^\í}gwG–eı!‰¤¨ş	k\ÔggÀ\î\Ù3Ó½_\çœ\İ;­\Ù\é\íiµ$:€ ‘\í\ÓòjY’\")\Z€	‚ A‚„#\á½÷ 72Œ‘Qõ23²P\0ÀKœ{n\İBÄ‹¬0·^šª\Êı¹z«xCBqMÀ¬Y³Ş´úÇ¶·¶ş\'™™™™\r\ßş¡`\Æ9X³fÍºª\î?JsÀÖ“¬Y³f\rÍ‡\Ì\Ì\Ì$«\Ì\á/Š·Ìš5\ëÍ®s`fffÖœ\ÃûIÖ¬Y³†fs`Íš5©µ9œ	<³-`Ö¬Yov\Ï\ì\'Y³f\ÍZ™Ã›Á“\Ì\Ì\ÌÌ†\Ù˜™™I\Îá‚zr›~Ìš5\ëM¯súÉ‚c°fÍš5t!s`0\Îa…Ö¬Yoj­\Ì\á­\à‰·\Î\êcÖ¬Y³›333sÀ9<x\ëlğ¤tÖ¬Y³†v+fÍšõ¦\×ù\ÌAsÁ9X³f½¹µc\Ì\Ì\ÌÌšsxğv Ş†s°fÍšµÔ¡\ÌÁ<Éš5k\Ö9<¡T¼M=fÍš5\ë\Âa333³\Åù\ÌA£\à¬Y³\Ş\Ü\Ú1ƒÁ\ĞPæ°£V‹µ\Ú9X³f\ÍZ›ƒD¨kÖ¬7½\æÌ5kÖ¤vÌ™™™Ys\àH%³fÍšµ\Ô9ı¤333³\Å*s\Ø	¡X;kÖ¬Y‡\Ò)Ì“¬Y³f\Ãy\Ç8·M`Íš5kç°‚™™™Y³\Î\Î\é\'ò\ÎÁš5\ëM¯ƒ\Ã\nóO\ë1kÖ¬7µV\æ°+pffffÃ¡ÃŠ]\Æ9X³f½\éuBÁš5k\ÖR‡a\ç`fff\Î\áœ‚™™™\Ù\æÀ#ŒP\æ°\ÛqÖ¬Yo^Í™ƒÁ ¡\Ìa÷ùÀ9˜™™™\Ö\æ ¡Ÿ˜5kÖ›^\çğ\0NÁ\Ì\Ì\Ìls`\â¯nşNœ\ë©`¤\0úÌ·™™}8§N±œ=º\Ô,xK·ıü\ë3ña\ão½ú—5k\Êö¼ÖšÍ¡´m\æA·Wÿ²f\í£U\æ`#+\Ì\æPúv¾·Ò»Ÿ™™\ã80\Ë92 \ìx]Nóz¶ó–r{!/^+\ëx°Şœ:‡p\n\Åug@³A”¾=|6WÔŸ¬Y§Õ9˜fC³A”¾õ\Î^(\êOÖ¬\ÓhmcT\çÈFŠŒ3ñ¼¥\İ^ˆošŸØ¿¬YG\ép\æPg=Î†A<ù\é¾xü|‰‘Íˆ¯oşSQ²fí£•9\Ç\È2\Ì]=3\rŒ”¨\í\Ş\åÕ¿\Ì\Ì.æ ¢ğ\Ï\ìi6‡Ò Í¡ü\ãÁz\ã\ë\àÉŠ\àŸY\Õl¥Ád\å\Ö_\çô“\Æ9$2ª\ï?\"«ñ8\ß[\áÕ¿¬Y»º9H®´#cú\Ù\Ï‚³ğ¼¥\Ù\êz+\Éşd\Í:I\æ``ş™=\Í\æPÚ¦Í¡¸?Y³N\Ò\ÊŒcd™\ÙJ\Û`>ı\Ë\Ì\ìr`\Ú)²\Ìl¥m\Úüû™™\Ùp*/lSOd™\ÙJ\Û.À<ú—™\Ùem\Ê1\Ì?3¨\ÙJÛ”9ıÉšu’\æ\Ìaƒoœ90—Ê9H„ş¹ö\Z“ztñf¿¾ø%˜î¼¥\ÙŸŒ†ú\Ñ\ß6ÿ.v|Xo|Ãƒ½Á“Š3¢ùÛ \Övƒ9Çë¯U\æ°W>‘ÿgFt\ß\ì…`šò¶[\Ş<Ç‹õ\Æ\Ó9<POœ%½$Sb\Ş\Öf3\æP\Îñd½¾t`\çÈš\î›\ãb-¶°9”o<Y¯\Ï×›fK·ŒcK\íbt±•±J8ß½«l\ã\Çz}\ê|\æ`?™E\Í\Ù^=ô\Í6õ?\ëÍ§•9T‘efsX=\Ì_ó\æ\Ë\Ú$BÿÌ fsX=4\r‘8¬7¾\Î\á\Ì:ó’+»ºóº\×80oÌ¡\àY\ÕQ\æğô§bh¡Y\á\Ş\Ó\É\à\Ù\ä\í\ç_Ÿ‹¥\'“ª\Ş\Èb‹˜y\ØS\Âş \î\Ü\ÃA1ó W\î\ÓÄŠ\Ú{ô|>\ßV)w“\âªP9Ç‹õú\×9<(<)9£šZ`X\à—û?õ½·F©r“Ç‚ÿFoŸ\Í\ç\ë5|j\ï»Û¿+\éw2`\n-c\'T\Ü\Ë\íÿGW_ce¾\ë\Æ\Ö>qRüM\Æv\ÛC&Æ”pO‰]?i<Xo|\ÏòÿÌ¨¦&:.obcA\Ú\å\ã~%‹\Ø\Ãõ\á¯\Èö>ºöZªw_c6À±¶ÿ]\Ï\Ö\É\æ\å³uM\×[&Tˆ\ïj\ß\Éğz©ú¬7¯V™\Ã><™q¦Ì¡c\âŒZ\äM#ß‡\Ê?z>”(Şº¦/¨:À\×\Íÿ\Ù^š›¯n\Ö&…\Å\Z\Ï\æ\å\Şù94S4\ËL)©p\Z£û\îö\ï\ã1o^\×\æ\Ğ2ö£Z”-\ã\ÇC\å\ã\Òw“5\\ø\"¶=^øl\È\ZŒ\Ù\ÄÅ³¹¾ooP»´\í\Ú\Ğ×¢¦ó\r\ïöfv5\ã7’ù\Äc\Şœ\Û\ç<™UM™ƒY”M\Ã_‡\ÊÇ™C¾\Ì6\âÚƒöÙ–L¨x×‡¿-ª¥?¾öÛ viÚ‹‹\ïjŸó0\Ø`\">ñXodú8£P({\Ú\×Ài\Ì!ª=°\Ïf\ÌÁ\'­—³¡½¤ø¶®\ï«j\Æo8\ëõ\æ\Ğ:s¸8F†9\ÖF¾•»\çi1\í}¶9$Ä³y9\Úóm\\\ß\ïóñµ×¼\ã2ol\ÎV„ş™A”9\Ø\å½3‡„ö}6\Ò<^\Ïr6´—\ß\Öi\Îq\Üù´¨>\ëÍ©•9\ì\Ì2ûd¦¼o\æ\×\Øg³\Í!)\Í\Ë\ÙĞo;\à‹)2œwğË¼±98¬€[Ì¢^Isˆj\ßg£\ÌÁ\çõ,gC{Iñm]\ß\ïw\Îú\ÙgÿYo|\Û/˜\'ÁYÕ¾\æ€òi\Ì!®}Ÿ\Í5‡¸x¶^Î†ö’\â\Û:M\æ€-)\ëÍ¡sûƒ\'óÿÌ¨ö6É©\Ì!¢=hŸ­\Èb\â\Ùz9\ÚKŠo\ë´\æ€òi\â³Ş˜:Ÿ9d“\ÌÁ._J\æ@±\Ï•9$ñr6´\ç\Û8­9 ÿ|\â2ol.˜ƒq”\Úuœ\åÆ‹Ò‰\æ`•/\Éˆö}¶HsHx=\Ë\Ù\Ğ^R|[§5ôušø¬7¦V\æp xò@CÀºªn‹\Ø]³E\ì:ı²¨:€ñå—«\ã\Ì\áf9˜ò\ã\æÔ¾\Ïf›CR<[/gC{Iñmİ\Ò°\Å\Åc½9t\Ø<™B\Å\Ù-b\çé—”1\Ş]ó²\ØÁ?NZö2‡ ¼·9Ä´ö\ÙB\æ\Ï\æ\ålhÏ·p)\æpºóu\ïø\Ì“µ9\ÇHàªº­\ÊlSqõË¢²v‹w¼4¼¢™Cûld\æ\àÁ\Ë\ÙĞo;\à†ô\æ\Ğ?wÁ;>ó\Æd¯\Ã\nd\È\"MÁ\á\İ\Õ[\ÔaGT¼R4eøT&\Ğ;\Û «+òˆûH´©\Ó=s1T‡‚Ï†OeúÆ³±œ\r\íQ1£\Ğ7W\Ôô\ßğ±÷r\ëõ§s‚\'£™\02‚]Á\âO\Ã².Nl\Ä\Å÷e\Êx[\É\í…×¸0o\\4‡}ùşf@ñn	Dµ\ã\ËY5‡ø¯Zwğ\æÇ¹\r[\ÅÁ@€‘N¨lA.n,\ìrñyXr ^¶\ã´ç«³cø\n:¹t^üº\áqg\êX\É\ã\Åzı\ëP\æ€K’zA‡wÙ´<<\Ùœ°\Ì\ïŒ\Õ~œ~úó}½6\×h{ñ\â…øU.˜Í„¥\'#\Ş\ã\Ãz\ã\é\ÜA\éxG\ßsF\ì¶õJ\é=òpeß…-jgĞ¾Ú©€£ôZ˜ƒJ®\ÕBùe“B›„´\Åe±\Ïø²ÎVW+\ìÅ»š¼\ï\\*¼s\Ã\ÉV\Ï´!üò\â\ç0~ı‰u‰:i|™³É¹õ\Ú\Ì;üjò¾:\ìˆŞ™$~ú\ÓÊšƒ\ÉÔ¤f”>\ãËœ=Î¼9´M|/>Ÿ\r–p¹7™ø†€\Èa¬\âÆ—9»,\Ía›\ØS­«\æ—WMï—‡Ø‘w.\é\Ñ\\Ğ‡\Ûğ\ëSÏƒ…\\¾M6üús\Ñ$f¬¢Æ—u¶u(s0‹vµ´6‡‚S™‚\Æ\Ù\Òö‰\Ã\â§_\Òÿ4µ\á\ä\"²„Ÿ~y®b2V£‹-bdñvÙ°ôdL<z¾ K¬7\î˜üœ\ïY\Ô\ÊÌ¢]m6\æ`v\Ê\å\ê»ÿ\"\'\×\Ó`y—¶Áğ\î…8ŒµÁ\Ğ\Â-14\ß,†ƒ<VX†\Æ\Ï >ÿ\å	\ÙV\Ö1ºt#r¾g3m\à\Éûj?ÿ\å±xöó#/<ıù¡t\é%õ¹‡\Ï\æ$˜×’nŠÁù¦€%Ê g\r)ƒXoYº;ß³Ä¹ƒõ9‡=Á¢\Íc4\Ì;¢\ìT”Y¼¥\Ş1â€Ÿ\Ò\×\ï4˜HM’1‘À¬\×V»\\ø7\Å\ãŸ\å‚{¼®0\Z˜C\Ò|Ï‚\Î2‡ü\â]EV\æ`v*£\Ìat©M¦›·\ÄÀü\rõ\î¢ù†˜c½\Ñõ\èR+™9f#‹9d˜e\æ˜CõK¢\"\à\Õ\ÒUµ[D\Õy\0·noû¦´k&KĞ“\ÅL\Z\æ\Í\Æs‡\ÈE˜U\ä\Í\á\ÒVñnÀY\Õs\ï\ä¡Å›1\rs@–€“[˜ıs\×5\Ï\Ìz“\ê\ê\Ü\Î3­Œ,^Ó‹O¾3‡cµ:\ç`aE\rø\åL\ê…Ç£\âş\Óiq\ï\É4ó:\ãE9vX\Ì+„±\Å6ñô§\ë0³ß½Ş–Y­2‡üb\Ì0c¢=ù\é~Oˆ\î™…\éò¹{Ee(\à,úğ\ÂmUgÀ‡æ¯ªoò­¤¹ˆ‹˜\ã÷\é\\‘maß—7\nxSz\å¡V›˜º\ß.fô¨k\êT\ÙrW‚föˆ\É{­Á\ë£\ËŒ\Ê\Ü?w->\ëq”–\Ù\â\àü5qwºZtM×‰»Sµ¢g\æY~\æ\á\0\Ùn\Z`üÇ—Z\Ô\Øa^Œ\ß\ëPıK•-\Æ=5ÿ\Ì\\\\|<N”¹/_“e\ç\Ü;0ù]E˜a¾ÿtJv.XŸÏ‰=ˆs\İÅ•¿)\ç\ë˜<\\T\Î®—›zõ}\ï¨z\ï¢S$\Şôš\\\\ód½8\Ì=M\Ãß‹½2n\Ï\Î|<;n\ï\ìY²n©\è™mPßŸù™Œ\í¶w¤\íŸ,S*\î?G\ÚG¾¾‘Å«d\àŞ“Iy€…|µ€=&Mµú\Îÿ	\Å~ó·\â\ÒÀ_%>‘&\Ñª? \rój;	‹‡\Õş\Û\í]\ê\×óód|©ƒ¬g£i\ä#U¯yô›|=j†e¹¯+«¬\ÍA¾3‡cõı\'S\âñó{!ô\Í^g;ÈøP\ì­}9ÿ¢8\ål\Ì>µ]U½«ƒŸ\éÎNiw\Ê\çM¿-‘õ)\ÌJc¨\ëş@\íÇ‰öÿU\Ï\ÖM\Ã‘1Ò¢q\àKqyà½¢ø®•YU¿\Ì=\ê-Š\ïj\Ü\ÕJ\ÕF\ÛÕ˜\ÅaP.ò¸ø\àc­ÿ,N\ß\Ù#ß¡/…\ê\";¡Ú\Æ\ãMµ\×<r(˜_ˆ…Gcd}\à\Ñó¥|}˜Œ™_\Ø\'·\ìğ‚4‡„×—-\Ía[a*–È F\æ€›l\ÜşN„\\\äC_*sxO½8¹ õ•5h?©\ê\0Ÿ5ıg\İA§˜ú\Ğz’\Ó1\\\\üR\ÅÓ‹•gk,2*/º§\ë\åkÿ*2¾­1ñ?_,Š‘-ˆ/\ä;÷r^²‡¾\Ù+ò\0‹ùŠzÇ·5\î_P5!>t}\ï{ò\İı\ã¢x³òğ‚j;\n\ç¨öµşc~®`\ŞPõcš¦>\æ#\ê\Ü\Z=ZT\Ö\Î\â^_ôº9¬À\Äz$\'y\âúğ·\âl\×~\Ñ2v\Ü2‡­j\0ğ·<¸®\ç}U\ï¦|¸s´FZN\ÕwõÌƒ~ˆ‹g\ë†şŠ|}7n\èÿH¶÷\ï\Ş\íM\Şo\r\Õw\ãù\è‰{­\Ş\í\Õtı¡¨¾Ñ¸\İ+r\ïl£\\Ôš˜oôŠı\İ\íÿ.µÿ9Tñ`÷Ÿ\Î\ä\Ûs\Ûwµ}8Aµwy\à35¶û>\"\ëjÁ[õoUu0?\İò8\åóú² s\ï48™ƒA\Æô½\'AP,Ê«r\0ó\æ 1²x¥¨¬Ê£Ş•Á/ò\åM§¸šª\ïb\â\Ş]“()­©X¾@{Iñm\İ>u˜Œ“˜Ô¾í©\0N\Ôõ©}Y.f°\\Ôîš®öıC\ëCõM<\Õ6…¤ö0O\Ì<£\ê˜ovı\ëCŸ«ò0q·\ì°\éÇˆö²¤\×M\æ°ôd\\!·8Ó¹O\\±\ÍA¾(€[\Ö\0\å\ÏJ4|\î‚©ú.Æ—\îxÇ³™Š\å´\ç\Û™\n\'\rò\æ\à\Ñ˜Ša€\Å\Û3sY-h\Å\à®\é\Z\ïø\à#-Õ·\ã!££\Úv‘\ÔN\ã \\è²¿1o¨ú\0\æ›]\ïš45\Ï\äütË¦\íÇµ\äœZ€\nÁb\\e]y\æe±\ïüq°\Ø*ö\×mUÏ¹\å‹\ÍaNuşÙ®}*s¨:§\Í\Ğ\æ0WTŒò¨g2‡t§Øšª\ï\êñ{\Ò<\ã\Ù:*F{Iñm­Í¡ôö\0óGÅ§t\\¼E™†sÀ0‡¨x”F\æ@\Å\ãd\àıg3dû¶ŠÅ7\ÎF9®j™C!ü;·¨ò\0\Ì\åQ\Ï-¯Ë¢\\¡|’¦öo5´4³WŸ÷\İ\"\Şm0;U`œ\Ù{e\n\å©k\Î\Î\æ]gŸZ\æ€Ì¡1T\Îf”\×uô;½İ®\ËT}—Ç–:¼\ãÙœ7Ño;`˜ƒO\Ü8FŸú¶NwK.\äKy\à\ÊC\çTuQœ8>\Òòo¡ún¼\á\ÅÛ‰ûÿÀ…-j\Ş!#\Ôs¦*2Î€4#{^c>¢<\æ§[^—\r¯ƒ$~O®‡¨ı±,W0•n\ØR\\\ß\áP\æP™¼2z÷É­b\Ç\ÑW\Ä[‡·‹7½*\Şüş7\â/\ßıF>şxûÈ«b\ç±WEeõv™A¼¢LÃ®¯\ÌAv0N6\Ö\é^U\Ş\ŞW/\n™Cc¨œ\Í(¯ógùò¦3\\M\ÕwŸóğgë¤¸ql\ÌÁ·=˜ƒO\Ü8’}\ê\Ûo\á\Ñh°ˆ$7ˆ\î\é†À\èx”†9\Øõ\İx\Ğ\Óúb÷#*>\Ìó¯Qk\Ş\"\âs0ó5o²[\Ş6‡¤õb´1‡¤şÀšñ‰\à\Í7)\Ş\Êg\Õ/‰\Ç^‘Fğª2_¼ıÃ«b\Ï)œ,\Õqp.:wJ\Zè…¾W\r†\İ.@—	—‡Fy\Ô\Ã	DS¾\ê\\´\Ø9©ú®\Æ\ç=“È­\ÇQñ|4\Úóm\ÜĞ¿\'Tßç£‡.Åc·>¥ñ9™\î\é‹rAKH\îœ:]\'´ü\ßP}Šq˜±ôd’l:*~!s€9T‰\Z9o¢^•9 <\ê¹\åûg/\å\Ëù²o\æ€\ÏCø\Ä\Ã’\â©\ÌÁ\ãk.Ÿ\Şõ\ã¶Ô¦\à\â­Ã¯Šİ§¶Š…\Ç#ªƒ\ï=\Êó™.€4‡ÁOB\í÷\Ï]\n•³Y›\Ã^iŸ\æ\Ë\ï\Ì\áıFtÊ¶€a\Åõ]\ÆÇ†¯Q\Æs\ë\Çé¤¸qŒö’\â\Ûú\ÒÀ¯¸qsğm\ìw^i÷L½Z\Ä]’;§a~ñ¡´Js°\ê+S 4nÃÚ¨ø*sóóı­Í~˜oöü\Ã|T\æ \ç§[\æ`Ö‡)Ÿ¤õ;zr\à<‰O<°\Ê\â©\ÌA§¦òò5n\\z\ë\Èvr±—Š…G#ªƒ\ï=)@\r\Ú]d¶9¼¤@•q\ÊC£|\Ø^ûjmsU\ßÕ£‹®9\ê\Ç\é¨x>:lt|[_’™C\\<=4o\Ì!¹½4¯ohş¦èš®W\Ğ\æ\à|Xf¦nf\ä\á\Õ~Tü\"só&\êõ\èl 0ÿ0\Í<s\Ë+sH¹\Ş7‡	ı¡\Í!9tÁ¢\ã\åP•*ƒ\Ê\Ë\Õ§·¨ó	\Ô_\æa²ƒ‘\"\Z†;+sNm\ïœ\Ü.g3Ê£1”\Ç÷J¼t:F³4¢¾Ë¸,‡x\Æ\ìúq:)n£½¤ø¶F\æ\à79ø´ö;ÿpX-\Ş\Î\é\â®4\ßøĞ¶9 ¾yLiœ \\|2^\Ô~Tüƒ–9\èyVI\î?óÍ*s\åQ\Ï-o2\ßõ­öÉ£?`>ñÀU9\ÄÅ“\æ +gYFÊ²\Æ\0\Ì\ÉI´ôd\"„šN9\0r\Z­\Ìû\Ó?\×PT\Ö@Z¥ti(Ay˜ƒqN\ÓI\ĞT}ø6dÏ­§©X¾@{Iñm­Í\å‹ÁùK\Şí©QÀ—¶t\âS—Ó§¼\ãCnùWU¯K\Z\ã\n‰\ÛvTüB\æ€C„JQ-\ç[\×\0óÍ˜(zEeg\Zò\ëÅ”O\ÒX°>ı¡\Í!9\ØdqñrªBPi¯y\\¢~»Ì‡6Œ9\à;\ÃX\è\Õw+\Ô`\Øû\Ó?«?2\ë–£<\ê]\êÿ$_—T\Ñ) s¤¦ê»Œ	‡x—e<·~œNŠ\Çh/)¾­a>q\ã\æ\à\Û\àŒO´vN\æÒ‡oKsõ°ğ}yúAo¨ı¨ø\È0?0O\Ì<‹\Ú\Ì7{şis\Ğó\Ì-ß‡²(ç±ŒV\ï\æıñÌ¨OidIñò™\Ãry\çñm\ä¢.ğ=	¸œi£F€6‡Cûƒp\Ë\Z\èA«P\Ú1\åõe°s‚©ú.ğñoÄ»,\ã¹õã˜Š\å´Wyz»x\ç\â+^\íis cùbp¾!±›©qÀwBŞ*d>|øö\ë²\Îy¹ğ\Ï{3NR\âü•i7*¾9¬À<\Ñó,ºÕ‚·\æ\æ#Ê£^QYÙ¾)\Çøm—“[Å®[\ÅŞší¢²ú¹¸·‹}g_ûÏ¿\"Ş©—û\êì·9$qá°‚~ı`•9hG)8KZ]Y³%\Ï\ÂJaVšÃ‚\ì\\Hİ°Ø¯À¬ı“»e\rP°3‡*‰÷Î©WH\è6ğ=–ˆgg¦~œ¦bù\í™~\Ùõ\ã«\â]˜DL{0*N\Z.4DÆ§4#3ò™ƒO|\èCò°‹>™øh\\½0\íF\Å\Ç»˜ø\î3g\ìıµ¡2kşa>\ê:•Eeûf/\åŠ\×SÅ©­b\ç1\Ü\äwh{ƒ*Nm\ëôø›\Ì!*¾­«j™õú¡¥9•¤£„‚¤\ĞxA\ÔÎ—ø\Î|Uœ\r\\ƒ\Æı\n\í\'Å¹»;Ä¹Î;\ÄÄ½ö¢²(zø8­)n\è\ß%\'\Â.\Ñ ¡Xjª¾\Ü\ç \â+ª§©X¾@{§›+B8wgwd{\İ3§\È8i0)û4*>¥©IY¼!\êú\Ş$\êzÑ§\ïî’‹ıœ\Â\Å0?=u¿[µµÿõ=˜;\Åm9®jõV´¿˜oöü\Ã|Dy\Ôs\Ë\æ\ÍÁZ?{¤),÷\êŞ£¯Šı\ç¶y¯\ß\ÂaE`\n2SpuóRëš—É-7fª+Œ\\n¼3©ò\ÉZ½¨WHwN\×\Ë6‡‹öa¥a\Ì@\Æ]\îót0™\ÊjõÄ­_\\¾ÿ\àŠ\Îòp´4‡°£¤\Õ{Nn!w²\Ü@Ú©“±‘\ïƒ\ÄB¾3yV.\â€WH\ãGr¨}XIô\ÍÖ«u³[®›•:G\\œ¯ Ö«\Ñ\æ°\âƒü\á„f[\ë\ÌA9Š¬dœ%…\Æg%¨,7ğÜ¹GCŒ\r\\M¸3uVt¨Å¼ò<y¿“Ü•\ÌwSs¼\Ü@;Q\ëw\ÌÁd\nŠ¥)8:Ÿ9*§Ó¾\'O–ƒ\'ş«˜} ;÷!c3\0\ßş\Ü3ş”e’¾;U§²	½ğÁ>\ZÔ¢öa¥\Ğ3u‘œ\ã+…]\Çƒp\ÖoÁ¬ŒÁ\Ñ*sÀÉ‰Ry¥¯RL\Ş\ëV\'#Œ8\àGn°\è\Û\'\Ïh8A\ãş*\ŞJ¡{ru\Í\Ø}B„½nq\Î\á\Ã Sˆb•9\èJ¥1µ3\åÂ¡\Æ?\È`0sr\á·OÔ¤b\Üû@\Å[)¬…9üE¾WVs\Ğ\ë™ƒ6­‘œCA»R\Z½÷\ÌÊŒ¼\Öûµ˜y\Ø\Ï`$\ß@\İ>Y#Q­¸mBs’\Æ*\ŞJaM\ÌAb\Ç¯„Ö¯1c\n¶\ÎU‹>¿øSh¤(Ô”Nüƒ˜}0 .q1qÀ·N·\Ë\ß6~:ß¬%\ã­º&\êÉ¹¾\ZÀ%N³~ó\æ\àd¶\Îgª‚õ\ØWS;QNj|]ôM_•®Û§\Îd3ğ\r\Ôm§S—5©x+…µ4‡]\Ç\Ù¾\Ã\ä¯0®–\æd%2¾òÚ‘rg£C+Ÿ5\ë\å\ê\Íd¸²h\Ö\í¾s\æ°\ÂN2†½\Õ\Û\åaÈ«:s\ĞÁKù\Ì ^[§\ÜúŠm?%¡™5\ë\åj\\Òœºß³jXKs\0pW&\Ö-\ÌÁd\nš·Šƒ\çñ¹BY•9„:\Æ}\áv\ã+…»“çƒ=\É\Ì\\VXº+n÷ª k\â9¿W{N¹\æ°U1>À\å–Í©‚\ËÁ™•¿\×Àığ­\'E‹\ZÔ€Y³.ƒÆ•\Éû]jñ‚\rVB¯µ9\à¬\Ûı8\çpUš\Ã\åm\ê‚*+\Í!œ	¤\ÕU5/\Ë\à+\Ş_5\ËN8»\Ì\ÌL1\îxl;!Z%Z\Æ~8Y·Oœ“÷:W\ãu\äü^-\ì<úŠ\\³2{@\æ \Ía÷\Ñk·¤\ÌŸ;\ßqtûªd\ãKbBv.ƒ|Ÿ\';p<®ûh|U³\Ü\èœX[s0À§AwŸˆ?_˜\Ï|xouù?b\ê‹qy\\ˆ­e0\â€+\È\nnK¤adT¼rc­3‡4ğ\Îö\Èc*Àj?X‹ß¥d0\â\Ğ={I\ÜÇ¢?š‘yP1Ë‰¬d>ğ\Êv¯\ÒGL\ã0¾\Ø!\r¢s\×\ÅÍ‘\Ã\Òı‹¾\éF\å\ÎT9¨‡	\Ñ$\ëªK\\#\'E\ït½wı(ô\Î4ªıi?%\îŒ\Õ\ÊwŠó¢\æ*Y¶Y¸-:\'Ï‰®©ª­±3bt±,[NŒ.´©¶\Ğ\æ\Èü-\Ùorò\å–uŒ>Y-º§kÅ‰\Z\Ñ9uNU\Ö`x¾Y\Ü•~ô˜^ø`+“²\r\Ù\Ş\à\Ü\r\Ñ3}Q\å%õ»$T;.ğú1Î·Gªñ\ÚOô5¿³ˆnŒÀI\nœG\Ğf%¨Š«\rü`-~—\Ò~(õT\ÇNñcû_D]\çªÜ…‹Ê¹ÀD\Ğõ\Şµw\äÛ€	~\ÜğŸD\ß\ìe²^ğ³k\ê÷\r:+\Åñ\Ûÿ–i\ã\Ö\ĞqY¶­¨n)À·^_úZ\íÿşÿP\ÔÖ§uÿS•¡\ê.X|ˆ\í¶\è\×G\×Kƒñ{\í\â›ÿƒœ\r½\ï\Ê\×ıU\ìkk8¥¾_\İwK.~\ÍÑºuü¸\Zw\Ó\ÖDc\ï—\êÿ0\\Í Ú±qºy—\êƒ\Æ\Ş\Ï\Ô|\Äü\Âüt\Ë\İ]O\æ`ü\æÄ“\Æ$t\æ°\ZW\"|0&3·£1xˆo…\Ê\Â\Åİ²X\Ä0e(]\ÚPŞ–“¯e\\fT}\nø\ÉwL†SwvŠ\Zşƒˆû	À\ïÿ…Œ‘µ]Dİw\É6l\Î6‘õKÁ\à\ÜM²\r§šw’u}18w-4ÿ(ş\á\Ö?‹º÷\Õ\ÅR1ğ\r¾\æ€`\Ùñ+O\Ş[F~T\åaø]Oª-\0\Ùf¨\ä<ÀüB|·\ìº5¾‘yoM6²`t±]¥\Ë60A·\ÉE\Ş^\äXnYƒ‹½S‡zûOü—P=\0¯“¤yø\ïd}\nø}µ\İû\ä\äJ¾),nÿ|pcø{Q/_›u©8i€s>ø\Ò*¾‹Á¹\Ò^\Ònû\Ê?vtCß‡\â\Ú\àWd|#xóˆ\\üra7\Ã$¢ô•ş¿…\â\ãó\æu|R÷û|ù»òĞjÀxÚ¯óós\Í-»\î\ÍxûğöPG­5tç¶†P\×ó„7Ä¥Ce\ïc \Ãe\rNv\ìP\æ€w^»Á\Şj=Q¾•i-U\ß\î¿\Ğfó†ª·\çD²9T\ß\ÚC\Æò\Ì\èXÓŸ\È\Øz§.“qÒ gò2›\Â\á« c$ahş†\ì\Ã\èŒÁæ¯®ÿ7™­\í \ã\0¸\Ñ\é\Ö\è\Ò$¦42=;®{5\ç£PW4¨v\0\Ì7»\ÎÕ¿«ù€ù™T6Ëˆ4‡¬æ€³\É6·¿¡¡¾ç¯¡²\0·¬Ê£\Şù\î÷Bup\îeğE\Õwp¯¶k¿ª‡;Ğ¨¸.¨X¾@{T\Ì(\à{1¨8ip\'å¤¦b$¡kú¬\êw\ÓÿIŒ~ \â\0ø}L,ê›£G4\ìh7\î[\Î\Ï9\âKaLyªÀ]ğ˜˜gÕ‰e³Œuc#\Òuñ»”6·ÿYÂŸ	s¨-*k€ò¨i\Ö9ª¾|£±Š\×ó®š\\8gC\ÅuA\ÅòÚ£bF¡ú\Ön2N\ZÜ‘}JÅ#	\Ú\n‚^´\Å\Ú\Üoƒ~ \â\0¸©©Y\Z®D3\Ì!\ß=_¤\ÍA—Y¼U\Ô€ùf\×\Ñ\æ \ç™[6m?®%Ö9,´¨3\Ô60\0\Ç`\İasÀ\0¸e\rPõ¢\Ìa\ï\éB\æ@\Õw›gT¼Àª<¯\îP±|ö¨˜Q€9Pq\Ò\0—e©\ØQ b$¡k\êŒ\Ø/û\İô?\Ø\ÕöeuôÇ uü„Z\Ô\Ñ8¢Î¯is\Ğñ‹2‡ñš|yª\rÀ]ğ˜\Ç\ÚşÜ¿´ı¸–`sp°¯fK0_\"\ë»p\Í“\Ù\ç¹©X¾@{T\Ì(T7¯Gs\Ğ\ï\ä¶\Şy4|>€Z|6ğ3üfa7\rÛ¦`ô†€oIB;\Å\ç´9 <~•j‡6=ÏŠÊ²9”\Ã\Òp\Ú\Æ1™¶Á¡)sp\Ë\Z <\î|7}PM\È`RRõ]\àKJU¼wòuqŞ‚ŠmƒŠ\å´GÅŒ\Âi™9Pq\Ò\à\Î\ØY2v¨IP\æ`õş±uI\ãO\Å1X¸!šFY‹\Ü\Ñ&>\'Tuf‹\Ø\å|?I\Ûxu¾<\Õ•9Pû\ç–\Í2Ö9\Ì\ßCó7C8\Ö*@¢¾û\ÃPYLd·¬©s¾«\Ø0÷\çß±^\"\ë»\èÇ¤&\îÁ;¾\0\İø6¨X¾@{T\Ì(œ–™\'\rR›#	:s(ô¿\á¨/B?Pql\à…¦\á\ï#p(\Î9\ÙÏµIs\Ê\Í7\ÅÜ¾Á|<\Öú\'rÿ\Òö\ãZbe·\ÔI&p\ç£r(sp\Ë\Z <\ê\ë~\'T0\ç`RJPõ]\à„UT¼8P±|ö¨˜Q€9PqÒ #å¤¦b$\á\îøu\ìoú\\q2ú\êúŠc\ß †ûBn\'\ßı5t\Ø\0¼A\ØÆo2\å©ø\0ef¹e\Óö\ãZbİ˜\Ã\Ğ|³\ì\Ü!kû“„ÿG˜Ã™¢²(z\çºÂ‹\ï\Zö¤\Ü_ûY\ß¾€Š—*–/\Ğ3\n\Ú\èX¾À\ç(¨\ØQ b$‹W´I¿$ö\É4?\îk\ĞTısWCø–\à\ïÉ¸öy\Üjo\Ê\Î_/Š`¾\Ùõµ9\èy\æ–MÛk‰õc2sk\ã¨\ì|\à‚cpg·¬©C½\Ó\ãöisVŸ3¡\ê»À\í·Qñ\â@\ÅòÚ£bF\ç¨8ivRS1’W•†°·zk\â÷“¢¨8.phq|\ä;\ÍZ\Ó\æ\0ƒ2¡3]Š\r¸\Ù\0æ£™g\Åe\Ù\Êdø¤[\×T\ê‡¾\Ğ¾}\Z€ÿ»\åÁ(œ\ë:ª\ã©%U\ßÕ¸ƒ\Î\'‹¨x>\Z\íQ1£€\Ìa9\í©Í¡„ö\ì6|¾H\ã\Ï<Æj×‡d¦ 6¸1ôÀ\İÁØ‡Ö±Sùò\á6\nİ¾Á|<Ú‚yV¼l+\0ü¸*~ò\Ì\É”9¸e\rPõj=3U\ß\ÒN\ßx6¨X¾(\Å¨8ivRS1’¶\rô\ÇE\ß\Üq\r\\\Z\\úFò7Rƒ¿%\ã\Úh;™/O\Å(s0ó,©l–±Ì¡Iv\î\Õ<ğGG[ÿ(á„9Ô¨ÿ»\åÁ(zQŸ­°A\Õw5\î\á÷g#*F{T\Ì(\à\ã\Ä\Ëih—}JÅB)\íaÜ¨XQ@?\ÄÅ³5><u}\èkõ1÷CÆµ¯3\åû¥\ÉPñ\İı\Ö\æ \ç™[>m?®%Ö9\Ì5©¶ø¡EšC\×û¡²\0·¬Ê£\Ïb¦\ê»À\äñgƒŠ\å´GÅŒ‚1‡\å }4½9¤EÚ…ƒ~ \âPÀ\ïS`ã“œ6Sqm´ŒÈ—§\â\î~c>šyVT6e?®%Ö‘9\ÜP\Û;Û˜\ç3U{\ÅõÁ\ï\ÄÉ›;%v)ôN_•³\åQ\ïRÿgùò\Z…úFSõ]Æ¥L\ßx¶NŠ\Çh/)¾­›zÅ\ã\éKV\ìp|JûÆµ\ã\Ò\èŸ¸Šg.«E~50\ÃÇ›ŞŒÅ­\Ñ\ãùòø\r_\ïwaÿ0\Ï\Ü\Õó\Ì-\Ï\æ°€9¨\ÈC\0kÖ¾ºyì¨¸:ô•\Æ\à\ß\å¢{ê›£‡Cñ\Üø>º}´šœ\ßYÄº1‡ş¹ëªƒ{f.13—\Ä“5\â\Ê\à—j¡—ÊSu\Ş\íQ\Ì\æ°èŸ½¦:¸g¦™¹Dn‹ü\Ë`±§g|\ÌÛ¿½bncs(?úf¯ªî¹\È\Ì\\2\ã–W¿_(¾p\ZO{ú¶\çr\Û\èir~g¹¯oŸH(¾0kÖ¬7¹\Ş&ş?ôô*7\ÂM\0\0\0\0IEND®B`‚','image/png',NULL),(5,NULL,'\0','Japan N5',2,5000,'Japan n5','japan n5','‰PNG\r\n\Z\n\0\0\0\rIHDR\0\0\0\0\0³\0\0\0„‡\Ùp\0\0\0sRGB\0®\Î\é\0\0\0gAMA\0\0±üa\0\0\0	pHYs\0\0\Ã\0\0\Ã\Ço¨d\0\0-IDATx^\í}i\Éu^ı‘ş,À{‚»\ì.¹”6)’a…,[\\‰9¸\æR¢\ä^\Ò!Q–M‡\"D“¶d+æ±’,K<l\ÒvX±W\ær\É\Å^˜À\à\Ú\Å}/\îk€¹00\Üsw÷\0\å÷eevgç¼ª\Î\î\é\é~ñ\ÍW¯ò¨¬\î|_¾Ìª\ê	~á…±\Ğ\à_\Ì\í‹-¶\Øõmg\Å\á£`+Ql±Å®o;PÀAğQ½#,,,ls¾8¼\äd[l±\ë\ÖVâ…N[l±\Å\Ô@ p<¥w„………m\Î‡—Lb‹-v\İ\ÚJL¢\Øb‹-vğP…………-V\â .\"qxE\Ö|D\Â\Â\Â\Â6¡e[¬\ÄA \\D\âğ#}@XXXXsğam\Û`Gš\Å[l±aæ °°°°\ÍJ>üª>¨Yl±\Å;O„………\rjG O\êaaaa›#qø;}PXXXX³‡\'µ!,,,l8P;\ZJ1\Ä[l±	Yqx\Â:(¶\Øb‹‰\ÃÿÓ‰\Äb‹-¶Ø°üy\ÂJ[l±\Å\ØQ……=ø\Ó\ïL„ÿûh*ü\â\ÆI¯üÂµ\ËJ_Œ\İ	±\İN\İe\Ó‹‘8üX.Àƒcw•8ŒB<ò\×.kCXØ‡¦g®\ßQ\ì“_¸v9xœv”!,\ìÁc\\M)~\í]-Nºğ\âa%\æ \Øb\Ç\Ùÿ\íà´šVlº˜)K}bW¯‰\Ãkú€°p®mB	ƒ\Ùşó\îi¯rÂµ\ÉYqX\ã$Š-¶mÿ\'n{õtÚ«¼Øµg\ê€@€wº\ÒZ\nøm\ß\å™ğ\ãk\ÇÙ²‚\ÚE°F\ï»‡?}=z®¡Ğ†EJL;|\ê®\r\Ä\á\'ú °°Å¾\Â`6< õ1DõW7+qX£\raa\Ãqk…¶ÿu,\åU¿põs v\ÅF\rf\Ãô‚«OP{V\ëaa›\ç²ı“\Æ	\ïóW/G\âğº>(,Lü±·Æµ›—¶=·y\Ò\ë<\Â\Õ\ÍJVkCX\Øğh:zÁª”\í¸øG¸z9P;\ZJ1\Ä›ğ\Ã\ã)\í\ê\Åm¯M³õ‰]{v8_\Ù>¥İ½¸\r\å¸úµ‡H~ªk\Ë\æS¿põsğ˜6„…mşò{Sá‘;aß˜?Nİ¼şŞ¶©ğñ7(Dı™\ßy„«—ƒ\ÇhG\Â\Â¯!ÿÊ©ğ49¼/¾qhZ	\Ã°\Ïy„«—•8.0òC öSô\ĞKQA! jøûïŒ«2\\½‚\ÚA$\Ô„…9şÓ½~Q\ã.…O}Âµ\ÃÁ£\Ú\ãÁñ\Â\Ï<ü£¦	\ïú„kƒ\ì(ŒPPl±û‡\'’Ÿyx§;S\Öó‰]v€?H[l\Ûş“}\Ó\á©\É/a!ù\Êq>±«Ç\ÄA\Ì&Š-¶¶P j0ò•\ã|bW\'\Â\Â.#>õ	\×j\Ç`­µ/¶\Ød¦n\ß\r\'2¡\Âh:¯NEl!ù+\ÃùÄ®;xD\ï(¦D±\Å6ö\ê7#¼Ú™\Î>\èôÁµ;a[F±9öN§³y“\ê»¶\ìH\ÌAaa‹\á\ì¿\Ü2‘8q882“/õW7+qxD\Â\Â.ó\Ğt8¹›\Å\È\Ô\İğğµ\Åöñ?\Ø1•\íT>õ\nW?j\Ç\0\Ê/¶Ø–\í\ŞÆ„ Z\0\Û\Û\Û=™²œO\ìê±³\âğ!°•(¶Ø¿\âüû;lq\â€\í\ï­O¬O\ìÚ²u€M[lmÿ\à\Ô\ìÛ˜I\âğL\'\Ö\'vm\ÙÁ‡°c%Š-¶±\Æg?™$›3‰õ‰][¶sğ‘·4‹]÷ö?\ë˜=¥À–$\Ø~S¦>±k\Ï\ÎE€N[\ìuòÿ<·8<dš­O\ìÚ³uÀ4ûb×½\İ\ÏL)°ü\èWŸØµg«´±J\'Š-ö¿\Ùÿ\ËÓ…\ÄÛ¯´O”µ=b/Œğ\ÛV¢\Øuk¿İ›\Ñn>{ó‡\çN\'\Ö/vm\ØJV\éƒ\Â\Â\à\Û	ÿ\í\ÊG0%ñ9pus v\ìƒb×µı‡{’ÿ™8`û\ÍM4µ`\ê»v\ì¬8<\ì$Š]Ÿv\ÇPü”›¯8¼r>\Í\Ö/v\íØ‘8¼£\n\×5ÿBs\áÿ®\í+jj\áy^\á\ê\ä\àamÿ‡C…†\ŞW°ı\æ–	¯ó\nW\'\ÓN\ŞA±\ë\Ö.4¥ÀVŒ8¼İ—)kûÄ_[‰ƒ\Ù}±\ë\ÎşhS\á)¶b\Äw=\â\Î\'võÛ‘8¼«\×-û˜\ß¶*F°ı\ë=S^\ç®>Ò†p}3{öÙŠ‡·0µğ8¿põqğ\í(C¸nù—7ğo`r[±â€©\ÅG[Æ½\Ú!\\]¬\ÄAPûø§[\'¸´Bø–\ç”[±\â€\íë‡¦\Ùó\nª‘8¬‹Œ‡5‹][ö·\çœû\å\é¢\Ë÷OøM)°•\"/e\Ï/vu\Úy\â \\›\ç3\ÛŞ‘™\Ø|?8“\n/fò°a0Wv†üH\Ú|\ÅÁ­«¹?ÿ¼\\;K\á§h\êòµ\ÃS\á\×M…OµF\ÓŸr\Âñ¨AM\ãöM©¹=€}.\ĞD\nÇ¶q3•ó\àşñ»\á\ĞD²\ÓûŠƒ[\×\åÉ¨œ®¥\0“\ÙIqy\Å!xP\ï\Ïÿp\ãDxò\Ö\å”ß§QÙ·\\%øwvN†¿KH\Ê\çŠ\ÃYj»ÙŒ\ÓI\ï#\\]i:•9f#©½Å°=A(|\Ë	\Çs$ú pQürWşO©=\Õ6\îU®qúgS‰ù\\qÀh\r\áÿùÛ¹\ãØ\Û\n‰CR]½c¹\ãI\í-†±»÷\êŒ‰…ü+qxP\Â\Åñ·N\ä=ø–«c´„X%\ås\Å£96ü÷*\Øş\ã·‡¤º0…m#©½\Å2¾DP¾ù…“9P;‚’€\êû4ZŸ¢ğ<\Û)5.\ÍÀŒ\âØ¦f¢p#¼YD\ÄtÇŒx\Ø[’8pu™(\Â\Ôe\ê6\à\ÚY*ğ]@ ¸4Añ\Ğ;Â¥ñ§:&Ôˆí›¿RŒÛ‘@R>[\ÌBdŸõG)ò1öqi\î–$>u™h\Â ©½\Å2\Ä\á›_8™£È¡I$»8:e¹\ê+\Õ6[R~#f!òútä¨¸³\àn8†4ûn¶8qğ­\Ë]˜ô½>‹\Ã*zò\Ì/v²­\"‡¬D±‹³¿Ialv\Äö\È_	û3\ï\åşÌ¶‰\ØüF0z#\ä?G\Î¡\à¦8†4\äA^³q\âPl]öÂ¤İ¾R¯\ß\Ø\Ø åª¯\Ş\í@\íh¨D±‹²\Í‹¸ôù°¿°kRµö\İt#pV3šcÄ\ÛL4`?¯À‰C1u!¯½0i·¯\Ô\ë>µ)\'\\º\Ø\Å\ÛYq¸\ß:(¶¿½‡\ÂXl¶S\Ú\éóacm6\ì»\é\Æn$qÀ¨m¼{”\æ\ry×ˆ+¥Ö…Hûvû\Üöc\Û	¡˜k}bqh\Ö…‹fó^Â—÷Oy\å¯jó„ºSpœ—\âpFpse\nmöl¶8\Øw#Š­\Ñö¹v–Â˜Ş™\í»µP{”\ç@)Â¥±Ù¾‡\Û#%ø#\ëÇ•³\Â\é’ò5d²\Ï!\Ä=\ÇÀmöó\n¶8\Ø\Ï4øn¦.#I\í-†mqøò)\ïr\Âñ\ÜO;\Ê.š?ó~nû&Ê¾\å\Ê\Í)1§‡8üµ).ß†¡\å\Èg/2\Ú\ì\á–^/0ks©«“¦\\;Ká—ºsO«~\íÈ´w9\áxV\â (\rpJ³a\í\Ë3ø*9Fq¬\'üñ‘c\0¦ˆâ·©;¯\Ìd\×Š\İğ¼\Êb\ÄÇ¹À°\íg\Z|7S\×Å±»y\íû$M\ì\ëò\Å\ïS¤\àn8\Æ\åø#‡}@¸(ş÷\Ú)±)qğ,WnnŒ¦p¸W{\Òy\éx¬»V¶\ßØ®\Å!\æ:\ã\å\ÜÍ§œp2g\Å\á>\'Ql?û\0‰n\rb\äó\É_	»k4Z\0öD\"e\Ò1İ©…­Ÿ\"‘¸\ëK²½\í\ÖwŒ\ìmı¥Lø…=ô(/v¼¨04‹]œûöÓ¿q’\Ây&½\Ò6\ÂgD³\0¡²\ÓkaÃšw}…\ì\ï\Ë\İ\Æu7»N\ßú\Ä\Î\Ùş˜ƒbûÙ¿şş„rJˆ:i¹\ë÷±7\\lB;Ì­\ÂW\È)\ìüµ0µøuL)t{\í\ë+d\Û‘\îöMl7¿\Øş¶‡û[u¢pQüGL«\Å5Ü\Ãc»¾\å\ÊÅŸ\Ü:‘}±	\â€\rû¸ğ‰-\äl”_ø*\ÂÚ¾\ÜKW\Õ´\ï!\ëº|‘\Ú-\ÑC\ïu¦¼\ë\Îq9hC¸8~±+­¢¬\Üc\İ\áÉ\ã^\å\Ê\Å\ë)j0\Ïq02«\èòA&T³8 }‡B\×k3\î\Ğøn_=ª#z…s¨AI8tmFİ·‡c¿pŠ\ÍW	üöIun¼\Ì3\â`\Öp\\…\ê”÷AÂ†K\Ñj\Ú÷€¾.|‚\"¦¦\îö]šò¡W— ÁJ½#\\iÿ”rBlˆ\Êcş\ï[~.Œ\å\âD\îA$\0‹’\ØÁ˜c[‡g²\åöU\ëV\èzm\Ëu ¬\ïy„8´\éƒ\Â\Şüzo:û²Fp8#\Ş6T#”Gù¹ğ\Úşh\Ô4O\Z\Ú\â€\ÍL-€v\Òô‚\ÊUµ8¸^›!À¥nYñö8°‡•\Úö\ãOl›˜\å\n8\ëw;S\Şõ”Â˜?c³_{v\Û\â¦5eT¤±\Ğ[Šš0 #{\Ãgö[4MŠ»\î\ß;4\î&q{¡\'\íµ·¤\ï\çš .|q\ç8P;‚¢€…H\×!1wÆ±‹\ãw”xp\åæŠ§w\ä´£·-\Ø\ìt÷[jƒh¡@\ÆÑª¬¨2@Z¹·¤ó	\"+ô°F8\ãtöÛˆXwÀ18\åÚŒw}¾a0£¦\0®8\Ø\ë\0\"›…9x^%!u7\ã¬\ÜõWR¸ó	GE\íú ±\Ø\ÉvÇ¥\è\ÍF\0Ñ‚½™\ãp\Ş\Ï\ï¥0Ù£>û\é9aÀ\æF\r€+\Ø\Ü|\Õ øl¸M9«uı/\Ò\á?0—©DÜ†i–©\çš\Ë÷³X\í(r°…\ãù\ÏÏ¤\ÂS\ädxò\İÖ ‡\Ç;\É)û¨ó=±iÜ«\Ş$F\Äp\Ó:~¬\Ånƒ\Îm·¸A\è\æ\Ã{\ä\Ì\Ê;_&A$…rq0×½‹œ\Ö\ÍS)`±¶\Ğ÷P¬ e\'ò\ç(À\è{\ê\Â0\æ\Íw©wŒ\Ğ4Ã¤\ß \'~c€F$§bFZS?\Îw–\Ú`\Îo·§‡œ\Şn‹A¢\'7s\Û>_¸D¢9±s÷¼}ı»®’88y*%ú¼\Â9V\â H\Æ/m›Po_Â±NjG\ë»Ş¡e2›t,\â\Ø==\Í\ÖYŒüvı\ÃxL[\×\ï2¦v^´‰\Ë\ÏDpe*	´\ë!\îñ\ïP\ä`®û_ššu\í•¢¼\Ï\ï›T\çv¿‡zF$\ëõ\áYü8M\r6\\š	OÜ˜QñM)0\Ç\ï(F\ÈP/Ã¢¤I_›òüñq-\ç?½k\"¼Ê¯ŠBqûün{ºFñ´f~{\Ì~ÿ¸É—_‘\Òù\ìü•²\Ó´sx‘K~ú=©<»”ú\Ëeÿ\Ò{Z$<¿¯\Å\ÊÁ½\ÚÍo\×e\Â\×g\Â\ã8ö»nÏ¨\Î\Ä\é&ÿ\Ù[¹|™|\Îûi\Z½®“¨\Øu\Ç\"Ç¶\ë·\í	mšVÂ’Ÿß”\ï¤6\"+WnŒ‘ ¢ƒ\ã³\Ûú7\Ó-Ì¾\îùD\Ù\ç{ª\î¥e\çñ\Z%Ú™ŒSi£P\ëX€›ÿ†vtDˆ’Î«\"F\à¼\\;lN,\0\íˆ+\ÊÎ‡@\Ü\"\ÇC;{\Æxq\ØIs÷ø|c\çµL\ì÷So¬\ÄAC;E\Ç\Éy0\ÂfA6Bb„\Çú»ù1\Ç6\éˆOk\à\Îy”Fq»>,\â™ú\Üú\Í>œ+k£v\\y¤qe\Ê	\Î\ÓK\â\à¦ı-M+ğù¸\Ç\ç\İ\Õ|œ¦\ÜwToˆ\ÄaCd¬\Ğ\\Ï¶†Á™ğbÇ¯\ß\Ñ\ÙX„L‘\0\Äw\ìü¦üd&—n\Â>Ë¥t^]6\Î\Ü\ä\Ûc\Û\çIX¸²6&¨q\åÁÃ“|¹r`‚¢-\ÜnE;!d—\és\àòUş¦3>¾e|Vÿ¨\';+Ë­ƒõj¯¡\Î\Ğ6˜	‘³%g±¹—\"\0„\ŞIÀ£\Ón9ğµ©ü²Aÿ\í\Ó\Ùó~ÿd^º\rü<\×—\Í\ÚA!\à	Å¤z0e\âÊ•$\nh\'—§Zp•¾#ô‡¹ô§Z·u\0†\æzµWSGhŠ\Äv\0Or\ÈFe,úq\åi”tó\ãùƒw$ˆ\íW\ã#¬[gûŠÚ‰µ†¸ú|D°Xà¹†\Ş\Ñ(b@;Á˜^\\qD³šğ_Nkñ\Ö(w«;0\ë™U\Ä0Qr„\Õ\ær\â)r¨B\è\'\áÊƒû\È\á\Üüp„óvrzVš·\â\ëµùM=¸ò¨­qõ\'pe\æ\n´\Â\0>§y\ÚÁ\å­|\ì}šú9ı¤\Ş8‡ú`2\"†¶Ar8\È5r\Z\ÅzF\ïªõ‚B¸9}—-ol<gÀ•CıH\ÇB—\àv¨[gŸ%g\ã\Ês¥©Š[Ş¶“\ÚS,®\Òôh\Ãõ@`\ãİ!úl0•\á\Ê.$^\ïO{õŸ\Å\ÌÁr\ìh#»_Gö«=©ğrˆh\äT¬1H!x!D?ğ:»¼m\ã®,±F:\Ây<5\È\å\á\ê\ãl,Zr\å\ã€(Á.o\×w•\Â}®L)@DA@û8\Æc\ß\\¹jÀ³&gõ—z²s\âP‡øö™irŠ…\Ô\Íp’Lxi‚-M\Ö¸o\ï–w\í«Sñõ½\å?}#£rÓ¹ú8û\ì\ÍÌ¬²I@şR\Ú\ë‹\Û}`\áµ\çvÔ¶ˆò\íó”~;\Å×±@\äğ±\íl¿©\ËôN½ñ3û&•#Öqxk™ğ2	\Ã8u@\ìòn}\ÆF\ç\ç\ÊgHLş\Ñ\Ù\çö©qq\Ë&\á\ÔõøúF&ı?ƒ8\à)\È3$\0¸>0Ú—dcÁ’«g!€ˆÁ·-f–uAPÁub¿y1M9·rÌµ1zû\0\Ù.Hñlók®¼V\í\íüm\íô“\ä<Iõû49˜]®’\ê»B‘W\Æx\ÂŸc\ß~÷ˆ\Ú!Ş†\Ğrõ-=¨\ÅÁ\ê/õh+qP°»ıwO„µCŒ\Ğ<{”:†ğò[>\Î ”«\Ã\0Ñ‚+úvú)r;\İÀµO‘ˆ\Ø\å’p“\"™¤ú¸2¾¸@\Ó´\íQlPÀ¾Hb\ÂÕ·ø³3Se\íoµj\çÄ—¸ˆ\íïœ›R}FJp?90BÀyUY«|œ}\êF\áz\İú\Ú\Û\éˆ,\ìô\ì¾c#Â°\Ë%\á2M\Üò\Æ>N\Ó\r®Œ/”8P[\Ğ\\Ä…m¬ópõ-^H\'öŸz±³\â°\Ô:¸\Ø\íµ4¥0\ÑI¡-\×A8\ä9²F&ùzlÀ9\ÜrX÷0\éı4¢º\éN\á\Ôy‚\ã\07se’€6\âü¸–,Š´1­€H\ã\ÚoÒ´„;O%q–\Î\ßt)ş\Õù\éğ‹L•µ¿Õª,\ÛD€ƒf‘\Û;®DpøZ4\ÇG\çÀƒqŒ<İ²#-\Âp\ÅñvM\n\Õ\Æ\ß-‘İ¤\ã\Ü>çƒƒ%\ÇfD\'qõ\áQo\ßzÃ©#\'7lP¼}…õ:µÁ\ç¼\å\ätş?:1\Íö—zµƒ¥\ÚP\Ê®û\0‰\Ã~F+ŒRI€³»\å?@¤X—³q‹«\Ë\Åğ\Ä¶ü9›õ\Ù\é\Üù1°\ËÄ¡‡®9®ı¾\ív\ÅGœÿa®Œ\Ç\Åñ¹\à\á(¼¤v	L¨¤\İMB¾ùJZ‰C9ú\×b±s\âPGü6M+ö‘3`#s.\Ó(†‘}?9ò\ï‰œ4\É\Æ\Ã=\\]ŒÓ»õ\rRhm\ç\ÃôD9r\Âù\á\\v:D\\y\Ü\ÆE:W\ÖğÄ¹[Ñ¹#D\"Qûjgüõ–\Ó~£?\í\Õoê•8d±\Ù\Ú_\ÄöÏ¨3À¸No€Q+÷ª¢Î”lc.\Ï\Õ\Å\ás\\}xFÀÍ[§¶#³ö¼«\à–qğÿÂ­\è¥\'¬-€§\"¹2q\ĞÑ‚Û¹Ú¸µ¬n±\ÒgØŸ\ï÷‘d7\r¤U?ø\Ù\ÅTø×Q\ÄP®şµX\ì@0\Íş\"·_\éM©©:9\æ·.#´U#\ì•İ™’ù @¸eW\Ç\Ç\ÕT…¯Ï§üB3^¹\ÆH951]O%m\îsšÿê‰\Øş!vd\r\ÚhĞ‰õ`\ãÌ´Š\Ğ\ÑJÛŒ¹(„a\ïp4\Êbü\Ì\Z\Â~·$\Æt\"®¾Mƒi%\Ê9\æ‰ñ¬…O»\r_!ñ<{#*‹\Ïq>w•í”‹\ËÙŸ«ğ+qÛ¿ºw‚F£\è˜\ëï¿’÷öƒ3\á{qn…\Ğ=JBÀ\Ôg\ìWºS‰é•°±nÀµ•Ã•IŒ\æ\éğ\È5šš\Ñgq\åm®-6°‚[\Í\Çü®%©ˆ±“¸\ìe›n‡+6]Wl¼\Z.\ï¸1+}\Û\å´z‡À\0÷\×\áğ»\Éi\à8»)]1cc$C~»¼po¿Pı¿sh2|\æÀDøY—^N\í\é#±ºDS\"®½q8w\ïaD¬˜Pi›k‡\r\\®/tq\é.\Üş öl;h \æ \â\Z¶Wt\\Wµõ†O4?¼\î\è,¬n>>\Ü~1¼wÓğk\'§T\'ÁHFøº›B{8O›\Îg\Êù0\Ö#\ĞÁÕ¿~(w=…òÏ•1½ñi¿\Ë\á´X4œ/.Ô®^\\—\ï÷SJÿª7[‰ƒ\Ùı\Z³—m¾>\Ò\Ú\Å\nBm9¾G‘:+\ØE#)&‰\Ññ\ßˆ.ğ\Æa¡z\r?spr\Öõ}ö\à„wy_†\Ãq\íõ¦‡•\Ój&T\Ú\æ\Úa#‡´ú¼¹ts\éoõb\ç\Ä\Øj\í×ˆ½r\ã•ğ\É\Æ\ã¬\0Â§;:\Ã\Z\áô\èX…FZ®“q0¢€FŸzÁxlŠ½¾gH\Ğ>Ô‡‘>\â¹\Ùxÿk·p›QV\ä¼ó\Ç\\[l˜\È\×Ç¥»0Ÿ¯ûy‹C\Ğ\Ğq3\\JP¼‰x\Ó-•°„°ûnºe7lUù\Z6\ßfÓ³6Õ£ò\á\ä\\ºe/¡:\Õù7İ¯€ô7²N_şü\èr\æ…\Ä.r€gFŸú\Ü;ô\çN\×ûÌ1¶\ÌBbp,š&F,T\Ğ\æ\Úa£›o\'EExÌKw‘\íOyı=¾¿³ıú—nl¤û÷wÊƒó{ôw\Õ?6&\Ö\Î^\Ï\æBş«\ÛIur\éÁ²\æÁp9!Ë­—¢F–·\\\Êg•!\ßÒ\×ó-kVùPoR>p.u®I\Ìw\ëE\ÖÙ‹\ÅÖ¡Q\ÕY°Ú½üW§o…Ë¶ˆª\Ï}({ÿ|\×U¯òp <4T(_¹ø8M-Ô«\ŞÊ‰+Ï…\Ú‰Cô†gR>\Ãø|\ïm\îW´„+6]¥©é¼Ïc5È¡on üLº\áe\íW¢şNb’”¼„4\ê\ïWó\áœ\ÈÿH\Ê6ş¿KÊ·t\ãõ\èz:n°\éJò@\â€ªr-qh\Ğ\âĞ \Å!Kµ8¨:™t¸x\äƒ8p\éÀŠ\æòğ\ì\Ösá·\éNˆ·5çŸ¿´\ãBøô\Æ\Ó\Ù6­i<®j\ì\ïo\êQ\â€=¤;v\ï Q(”¯\\|€\Ú}\Ú?_\\¨=]$¸~¼À•”\Ïğ\ëø©\èšÆ“\ê³_\Ù\Ô7«\ß!\ÊP}S‹C–jq@Ì¥Û€8Dıı\n›n€s\"üƒK·¡Î:µ8Ä¡A‹Cƒù\â\ĞB°Åög¥[v\ÃfGœtcg\Åa›#Lş<qˆ©\ï±Æœ#•\Ï\í\è\n·+\ç\Ê,sm2xnG·\êĞ…`\ê\ã\Ò\Ê\r¼¢\ra;@#:œ·ÜŒÁ¯C\á\éHsœk‡\r<\Z\áÁ³\'\\ºóù~qû…ğù£ƒ\áŸš=\à<\ÚxF\rD¦ÿeû¦‡˜ş™‡­80ùó\Ä!¦>@‰ùO8\Ä\ä\ÎMu¶;\â\à\äo\èp\ÄÁI–\êÅ”¸T‹\Ã=ˆÃ¬tË†8 ÄK76\Äùpq\\ºm/\Ñ_@ƒ7})ºû%\ÎOo<ş\å\Ñ\áğ/„\ï_J‡\Û	•\æ¶ş\Éğ/\r«órm2@TEÀB0õri•\Ş÷À„ı\äÀxx¬\\\Ü};w8;a°\Ï[¼pv$<+÷yme?ÿ\'\×Súıjú£\Û?Á\Õ\ßI¸tÛ¾G‹Cƒ‡¸ü\rZ\àIõÿB¸tcC”ÿ’8p\é¨ƒš—µ‘8P#\îÁ(\ÈÁM·l9@\Í:H˜ô\ì>5õ!/—n\Û\r˜w£\Î\r:rp\ÒW5u²_b¹\0Û½\ÂüF7\ß]øŠƒ©—K«ğÿ(^£‘}„®‘\Ëp\Úg#£m¼JÏ¯’\Ø<”ü}<\Ø\ÔE\Ô7—n$q°ú¦\İ?\Õ>9º\ê\ïˆ˜t\Û^²•\Äı\İDNºAS\å#ÿ\à\Òmùp~90\é\ÆV‘\ê\ÜD\âÀ¤\æ`–q˜•n±ŠP¹%\Ûâ”¼\Ä7sB\î\Ë+~w\×`ø\Ù£\á{\äl\ï_\ÊhN—\Í~·w<|n{wø¹­~\"qÀCT\å“\Ø\Ô_(_%`¿\Ù=¾Õ“RüfwÄ¥\ÚxR·_5øœ\ï† ?‰Ç¯Z«_•ò(Æò\à‰\ÏÆ¾\Âb}ÿz\Z\éÑ‡-q\à\Ø‡¤|\à{qˆ\Ëg\Äş‘”lüWELºa9\àÜ–8Øœ/­Ä­Cª\Ò¨l7İ²—¶_ò‘ p\éY›\êD¾¥\ë\Æ2ù¨NuQˆZ˜t„y\ÜWN\à\ï\rÁ¡É¹\Ë\Ì?\íò‹¾´£Ku\äB0õsi•~¤\Ïk4õE\"aœ½T\Ş2}^ˆ(\àô\Ü9\rğ(û\æsÆ\Úpùm\à¹\Üş,\æ{Y\Ù\Ú_D§¾Ì¥[v®¿\Å\×5ü§\írb}\à¬ÿ¶‘/3\éY»ıR\ÔNª“KWâ…©¼Bö½-Ã•Í½\á½X\äi(©>\î«ğkÈ•@G\Ì\Ü6®\Û\È)¶QôÀ¥\Í!µ\\L…k\É\É\×vMGL\Î^¬½×¢¯Œ;\Üùp‹S\åsòø\n®Œ\Ş+±óûŠ\Ã\ê\ÆS‰ıs1Ù:P!@>\Ôt.v*ğx\ã‰ğ¡\æó\árRc®¼‹\å­l=•€Z«\0Š‡/’8põ¸@h¿\çJŠ/$:o\áM\Ï\èm\ÏRO:* \İ0¦\nöyN`\Ø\é.\ãµl;¿\r¬•@\È\ìü¯\Ñ\İ\×\Ò\Ãö\ÏÅ† Aï”›l¾À~°\Æ\ß\×\Ü[\ß2…š»*¾\Ş`+ñ>\Ø2˜V?\ÚÊ¥»\Ø8Àw\Âg·tª´¿=;~û\èPø\É\Ö\ê8Ä«§Zù»z$œ\Âõ\Ü\ãá‘ğ½±wš\æ÷)ş»\é\Æ\Æû\Ñ\çš\É~¾xd\Û>~ÿ\ÂNwó\ãw\ìü6 nş\×/ø‹Ã£Mg\Ø~º\Ø8‡6}°G~¬©´\çn\îœU\"‹ùXgpÁu*[u\'ó‡˜\È\Ó7\ïk®‡r°\ÖñjF$\Æ\éóaü])…­C©Y\éø\á®\Îù\Ä÷O^f¿\èqı1±‡m”ƒW\Ïqt‡ ¥$2Ph.\Ï|\0+\à>ÀÈ³…F<ºÌ¥»ˆ‹!pùk\rX@\ÜE\Îa+‰\ÃÏº¦	)ú\ÌR³\Òq·«s>ñ=q\0V¶ô&úÁb\à@\íh(Å˜ƒõ\îƒ,¬Gpió<ˆ\ãƒ\Í$›£A\å\Ò]lˆ<	y’FN„¼\\¹8 ÿ\éx·\"£\Ê#œ\æò\êÀ \âÒŠş1\î®\Ë\ÑKP.CDz!‰\Ã ‰ƒ“ÿ_\ìõ—;.O¨Hû~\\<\Ğ\ÒU²¿,m\ïk\éV‘2ú:\ê‚\Ø\Ü\Û\Ú\Ï\æ_(;+K¬ƒ¥\Ø+Z{\Ù±V0l\ZH«\ß}\Ä\ïNr\é.\ÖÇˆÃ¿\Ü\ŞMõdT}ø•dü\äW\ŞV\ë¹\àüQ;¢ò\Óq›+\ã\íÆœ\åP—§\à98:fÂ‹PGöf5·ŸJâ°™\ÄÁM‡]6”Šg=Ÿ=\Â\Ø\êÿ>şy„¢\á¤iò\Çuô<0«ü|Û‘8´\ëD\âR\í¹N\'ª\r\êg\Û=Ğ¡œ\ï\0D?M_…\ÄÁÔ§Õ˜ò\0„«\ív~—!\Zø/\Ö\\yƒ\İÃ¹öƒñ¯¸|\Å\0oE\Æ=‰C*|\Äa‰÷„$\îVpõ\Î¸\ï%«š\Ï%úƒm/o»¨D«\'ˆœ!&>õW\Êğg‰•XŠ½Œ.»ÀZşa‹6’SÁ±0\Ús\é.\ÚÄ¡ƒœ\ÔÔ·‘Fr8\rWnù!\İ\Í\ïÚ›‡(‚ !\á\êÀËºùñ‚—7	 ½#\éğğµLxšÎ…g\à\è\ïğ8wÄ‘½‰\Ä\á\'9ü„Ä¡ƒ\ÄÁM76wJ#\î{‰ƒ\Z\İ=üceKÏœ\Ôqbı±\\v€upü`‹ÿm\ËZFgÀ©6\à!DSBh\ï\Ôc\ê3|\âF~y¬\rpù\â¥vyLE\ÜüøP\\\Ş$\àyƒ÷\á\ÔÉ€wGll›qW§¹JE·\ãò\áfr\çª$ş\ï™ö{‰\Ã\Ã9<\Ôr^EX8\Çş}­=y~Q®©ö*š\Âp~WiV\â0W\àƒ\â.ª–q:Œ6SøI8.\İE›#x#ô;\'†\ÃW:¯‡û(ú@=6\ãş¾]^ıƒ&_co—°ğ\È\å\ÇOÁ»ywğ^G{¿t›ks¥ñ•=\åqdL¯I/mP\ë\\Rp_k7\ë{•DY\Ä\áC\Íg\Ùªv\àö!:\Ås\Ûgÿ8-\Âq!\ç\00\Úq\é.Úœğõ«û/²ùj˜N4Q$€»6\ï\ÑÈ¯I\Öl\ÛHG¾u})\Åx\n\Ó.ÿª“;W%ü±¿—¹bu\Ó)öx©€\Ğ4\àpşW)Ôµ8|—Fl¬\ê´^S‚4ûx9\ÈO?~ı@?\Íû§\Ù<µL§~|>ş\äüt\ØA\Óó®‚E˜>¼Ù¦<\Ó*Ÿ\Ë\ïôâ¨\\~0¢¥#Wñ:8As%\í-C*zsûGµ\Ówø\Ûò¶>IÀ°h\ÅõË¹¢,\â€\Û:\Ü\ÅT; ø¯\Ø\è\ä6C$¸\ã\å`ü\Ü:şY—)\ÃtXt\Ş\àct~®]I¬\Ä\áœ=¾Õ›sr\åğ\Äk!:=ß¡r&¿)\ßASµõ$6\ëû#n\×\\	\ë=\\ÿ¨6<±\î˜—\àX\Ö\Ö\Ïúi±(ZV¶ö¨H!©‘µŒXmGG¯†S \Ó\æ0¿öV:¿O;\rc­ˆ\ëÉ‘Ûµ3\ÃÉ·Ò±ˆ\Éù\Ôñ\\zœmò›òX -÷õ\ÅÙµ\"\Å\0\ë€s†D\â°^H`„.ı\Ôb¹ñG¯¨°·õ0jc¡\ÎXÀ\Ûv)Zõ\Ç*<n;¦•Ë›„c4b£,Ú±a€¦4o£N»Œ\ßQ\à\Ú\Ón”Ãš¸\\ö\Ìg‰\È\í\\O\íõı¾ğùš\ëCy.™J.6\àEEExø7\ÇVUa\Ü\ã$\Zé–ø\"Uµ\ãù#Wh\n‘VÀ‚™°°˜„ƒW3\ê\înÇµR§3åNË•uûúvyÔv\à1c. n\æü(.O0j\ã9.­\Ğ~ˆ#nŠ¸|6wø¥+óy\áš\İt\æ3\Æ\çƒ}.\Ïb\0z\ÃúXÿN²T\0…Á\Â*Q4–’½f‘E6§\ÈÁ8•a\Ó\éğ|\Ëû©Ãºù9\Æ\ÃE¥\â\êã¶[n\×p®~¬$•·y…\ç¦Ë·œ}=Åœ¼s\ç\"g†cC0Û¨­$n­\Äx¸\ÊN\Ï:‚\ë0\åñ´¦}>|\ítŸòCÔ¸v\Ù|Œ\0±\İJ¢”ç¾—\Å,\0–l¿æ°´½?¼·­\Ğ.£}S\â`€\Û%÷·v©„{‹5b0ø6E\Í\ä-3YFø	GÁèc‹vO\İüqŒ\În—·O8r\åğ³±²Ç•·ù\Üz*\Æ\Z‡)§+”\ß0„\ÑÚ‹_~Â»?\êœ&€\r¢w(n?\Ö\Í1\ÚğÚ…üòx?\Ä>\ï.²\Ñ\ÎC$\Z\æó\ÃI\íD\æóÀ¬qŸ\ëb`E[OÖ¯\r¯ !X…õ\ÃF~ı0Om>\ŞW\ä?¦­E(q\èC\ç¤Nd1#:BR7_!FG6\å\íú°\Æ\åo\í\Ç[˜ZD\Ó“\ß-o\ì£„\Ì(uD,¨\å¹ü¶\rD98§\ïùÀ˜ú\à<¸›°\ÎeœùË±]ù_•£ó™ò\àw{£:\Üòûühò\Û6\Ä\Å\Ø\\{q]ˆhŒò\æ:\íüõ ˜,Ù E¡½\'\\\İTø](V;ğOlşûñ\ápmÏ˜<¶k3:&\ÏFK7_!\ŞG£•[T#¸‘\ê\0‰W\Ş\0#?òat„\á|°ñ\ÚË•1@z‰ƒ¹.”Ãˆ\Ì\åÅ«\Ôó±yÿrğ6r28ô\Ë\ç¦c\íWÏ§”“n \çŒ8zª‘X\\y¼„eÎv¡}»¬ö\áóBûq\İ\æ˜\rûú\Ì1”WõX\Ç\0ô®Ÿ,6@jñ\ì .\Å\á¯I0r¢cT#\" oŠ\Ïgm\ì‹:û{4§6\Ç1:\â8\ã0‹+‘ş\à`I\ç\Ã;˜\ãcM@97\Ö¨ş7{,t\'\Û\Èo—G}‡¸ü{´\ãü\æ|v»\Ğn\\\'\"-·½`\\ŸYK±£¬=\Ø\Ç\ëEŠ}¬n\Å£!:Fµ2Ff8o\\:^–Z§\Å\Îm G\Æñu®ct\å\ÊCXAs\Ü<\Ä\å7#w+µNm\Ê=§lCP¾‘\Êg\ë)P~ß•\èü\æz± h7\Ò\Ñ.´?‡o_¿i/òa*\"\é¨õ!\É_/\âP,\êZªX8C\'»i{\Èq0KG€tˆœ\ËM\ÇóHG=\æ\ê1N\ç\âı\Ë3\áK\çR\áK\î¿v!EN\Ñ7r\Ò$~›ò\ïhJ¡\Ê\êòfÿU\Ú_K\îNp\å!87\Ö0=€ ™\ëF:\Òö)\á€\ä·w\ã`	!ƒ­Äl\ìCHP\Ä\Õ\äq\àQ—\â`ğ\Ã\ÓcjD“aa¼öv\n¥1@‡U£;GH\r\Û\Ã9¿…Fdû¸a3M@y8—›n\îj\Ø\Çq¾”\×\Îg/D½tn:\ç\ä„w{SùN\í89~\Ğ\Å\Î\ï–76\Ä\íq\Ë\ï%!Àu\àóÁ5 v\ä`Ú‡ü(oli˜¨\Ç!ø¼ŒÛš°±¦[ÄG]‹\ÃN©\çú\ß\í\ÍDLh!mŒ\ĞpŒx`<1‰\ã˜ó›\ãX<4ùwi2ùmV \ëÇˆkc1\å‘\î–s\Ïo‘Æ‹gS\á‹\ä\Ì/ N˜ş¨\'Á\ä´\à7ºÓ³ò\'\Ù?¦h\Ä.ú\Ğf<Ÿ`_?®6\ÒMû\Ì\ç\È66¢\n\Ó~óyõÁ\î 6~¿“\ëõº‡ÿ©\Å\á\ê<Ái££cG‡£\Ã#w%\ÌqDÈp\Ü\ä‹c„\Ï(g2\ÇN£¼™\íüp\Z\Û6Œw^\Ğ\Î~Aó»\Ôõ\Ğ\Õ\ÆyÀ¸;Á\åO²±v`Ê£>ü„\Úo_\'\î<\àzn·ù *h?\"„qÀ\çk_\"2|(ó/vö…58~~÷Xøó{\Â\Â\Â\Â?§\raaaa›ƒŸ£egy,üÿd\â*¢Š˜9\0\0\0\0IEND®B`‚','image/png',NULL);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_log`
--

DROP TABLE IF EXISTS `course_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `jhi_complete` bit(1) DEFAULT NULL,
  `raw_data` longtext,
  `user_id` bigint(20) DEFAULT NULL,
  `course_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_course_log_user_id` (`user_id`),
  KEY `fk_course_log_course_id` (`course_id`),
  CONSTRAINT `fk_course_log_course_id` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
  CONSTRAINT `fk_course_log_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_log`
--

LOCK TABLES `course_log` WRITE;
/*!40000 ALTER TABLE `course_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `course_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `databasechangelog`
--

DROP TABLE IF EXISTS `databasechangelog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `databasechangelog` (
  `ID` varchar(255) NOT NULL,
  `AUTHOR` varchar(255) NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) NOT NULL,
  `MD5SUM` varchar(35) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `COMMENTS` varchar(255) DEFAULT NULL,
  `TAG` varchar(255) DEFAULT NULL,
  `LIQUIBASE` varchar(20) DEFAULT NULL,
  `CONTEXTS` varchar(255) DEFAULT NULL,
  `LABELS` varchar(255) DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `databasechangelog`
--

LOCK TABLES `databasechangelog` WRITE;
/*!40000 ALTER TABLE `databasechangelog` DISABLE KEYS */;
INSERT INTO `databasechangelog` VALUES ('00000000000001','jhipster','config/liquibase/changelog/00000000000000_initial_schema.xml','2018-02-10 15:37:53',1,'EXECUTED','7:f9f510f74a2fd3347deeb54b4dc034a0','createTable tableName=user; createIndex indexName=idx_user_login, tableName=user; createIndex indexName=idx_user_email, tableName=user; createTable tableName=authority; createTable tableName=user_authority; addPrimaryKey tableName=user_authority; ...','',NULL,'3.5.3',NULL,NULL,'8251873197'),('20180210073753-1','jhipster','config/liquibase/changelog/20180210073753_added_entity_Config.xml','2018-02-10 15:37:53',2,'EXECUTED','7:8e0cc9b79b6154bf4ae1d21b3efc0982','createTable tableName=config','',NULL,'3.5.3',NULL,NULL,'8251873197'),('20180210073754-1','jhipster','config/liquibase/changelog/20180210073754_added_entity_Vocabulary.xml','2018-02-10 15:37:53',3,'EXECUTED','7:a0b0426d262a14d18417324700b2680f','createTable tableName=vocabulary','',NULL,'3.5.3',NULL,NULL,'8251873197'),('20180210073755-1','jhipster','config/liquibase/changelog/20180210073755_added_entity_Post.xml','2018-02-10 15:37:53',4,'EXECUTED','7:a3e3e057a6501692d422257d7228e830','createTable tableName=post; dropDefaultValue columnName=create_date, tableName=post; dropDefaultValue columnName=last_modifier, tableName=post','',NULL,'3.5.3',NULL,NULL,'8251873197'),('20180210073756-1','jhipster','config/liquibase/changelog/20180210073756_added_entity_Comment.xml','2018-02-10 15:37:53',5,'EXECUTED','7:1f2e92a93db256f126cdbd8be79d8d34','createTable tableName=comment; dropDefaultValue columnName=create_date, tableName=comment','',NULL,'3.5.3',NULL,NULL,'8251873197'),('20180210073757-1','jhipster','config/liquibase/changelog/20180210073757_added_entity_Room.xml','2018-02-10 15:37:53',6,'EXECUTED','7:6015eb104decf9b1cb3f899febe3336b','createTable tableName=room; dropDefaultValue columnName=create_date, tableName=room','',NULL,'3.5.3',NULL,NULL,'8251873197'),('20180210073758-1','jhipster','config/liquibase/changelog/20180210073758_added_entity_Course.xml','2018-02-10 15:37:53',7,'EXECUTED','7:b38a8c3ab4fa5b1e8332889d37935137','createTable tableName=course; dropDefaultValue columnName=create_date, tableName=course','',NULL,'3.5.3',NULL,NULL,'8251873197'),('20180210073759-1','jhipster','config/liquibase/changelog/20180210073759_added_entity_Lesson.xml','2018-02-10 15:37:53',8,'EXECUTED','7:d606680ec866f77583b829fdd2fb465a','createTable tableName=lesson; dropDefaultValue columnName=create_date, tableName=lesson','',NULL,'3.5.3',NULL,NULL,'8251873197'),('20180210073800-1','jhipster','config/liquibase/changelog/20180210073800_added_entity_SubLesson.xml','2018-02-10 15:37:53',9,'EXECUTED','7:c206fa4e2f1baefdeecb142533030855','createTable tableName=sub_lesson; dropDefaultValue columnName=create_date, tableName=sub_lesson','',NULL,'3.5.3',NULL,NULL,'8251873197'),('20180210073801-1','jhipster','config/liquibase/changelog/20180210073801_added_entity_Feedback.xml','2018-02-10 15:37:54',10,'EXECUTED','7:962e1cea814074c2be08def7bf267077','createTable tableName=feedback; dropDefaultValue columnName=create_date, tableName=feedback','',NULL,'3.5.3',NULL,NULL,'8251873197'),('20180210073802-1','jhipster','config/liquibase/changelog/20180210073802_added_entity_Question.xml','2018-02-10 15:37:54',11,'EXECUTED','7:c70ae63fbe01baf3fa4b95e4a7967336','createTable tableName=question; dropDefaultValue columnName=create_date, tableName=question','',NULL,'3.5.3',NULL,NULL,'8251873197'),('20180210073803-1','jhipster','config/liquibase/changelog/20180210073803_added_entity_Answer.xml','2018-02-10 15:37:54',12,'EXECUTED','7:c4b57924e53b3eaa0ed38b7de2ad1533','createTable tableName=answer; dropDefaultValue columnName=create_date, tableName=answer','',NULL,'3.5.3',NULL,NULL,'8251873197'),('20180210073804-1','jhipster','config/liquibase/changelog/20180210073804_added_entity_UserLog.xml','2018-02-10 15:37:54',13,'EXECUTED','7:a3d5866c6cc635ff0a83af9f8526ca6c','createTable tableName=user_log; dropDefaultValue columnName=create_date, tableName=user_log','',NULL,'3.5.3',NULL,NULL,'8251873197'),('20180210073805-1','jhipster','config/liquibase/changelog/20180210073805_added_entity_GiftLog.xml','2018-02-10 15:37:54',14,'EXECUTED','7:dd83c6b8bed48b11a200a1e763d3a991','createTable tableName=gift_log; dropDefaultValue columnName=create_date, tableName=gift_log','',NULL,'3.5.3',NULL,NULL,'8251873197'),('20180210073806-1','jhipster','config/liquibase/changelog/20180210073806_added_entity_CourseLog.xml','2018-02-10 15:37:54',15,'EXECUTED','7:d58db0b33659a84e65592759dd58012f','createTable tableName=course_log','',NULL,'3.5.3',NULL,NULL,'8251873197'),('20180210073807-1','jhipster','config/liquibase/changelog/20180210073807_added_entity_SubLessonLog.xml','2018-02-10 15:37:54',16,'EXECUTED','7:84d5dbea0c4aee764fd8e3f1ef866226','createTable tableName=sub_lesson_log','',NULL,'3.5.3',NULL,NULL,'8251873197'),('20180210073808-1','jhipster','config/liquibase/changelog/20180210073808_added_entity_LessonLog.xml','2018-02-10 15:37:54',17,'EXECUTED','7:4fa225b3a3a45866a8977e338768ab3e','createTable tableName=lesson_log','',NULL,'3.5.3',NULL,NULL,'8251873197'),('20180210073809-1','jhipster','config/liquibase/changelog/20180210073809_added_entity_Gift.xml','2018-02-10 15:37:54',18,'EXECUTED','7:e14288b216e7acf2fe608b4c4131693f','createTable tableName=gift; dropDefaultValue columnName=create_date, tableName=gift','',NULL,'3.5.3',NULL,NULL,'8251873197'),('20180210073755-2','jhipster','config/liquibase/changelog/20180210073755_added_entity_constraints_Post.xml','2018-02-10 15:39:50',19,'EXECUTED','7:90fd2b44d4e54eed477c577f58d2e838','addForeignKeyConstraint baseTableName=post, constraintName=fk_post_user_id, referencedTableName=user','',NULL,'3.5.3',NULL,NULL,'8251990398'),('20180210073756-2','jhipster','config/liquibase/changelog/20180210073756_added_entity_constraints_Comment.xml','2018-02-10 15:39:50',20,'EXECUTED','7:16b08622d205dd4912fe485c3310acc9','addForeignKeyConstraint baseTableName=comment, constraintName=fk_comment_post_id, referencedTableName=post; addForeignKeyConstraint baseTableName=comment, constraintName=fk_comment_user_id, referencedTableName=user','',NULL,'3.5.3',NULL,NULL,'8251990398'),('20180210073759-2','jhipster','config/liquibase/changelog/20180210073759_added_entity_constraints_Lesson.xml','2018-02-10 15:39:50',21,'EXECUTED','7:c6e8d6565e133342d327361a3d1d8b5e','addForeignKeyConstraint baseTableName=lesson, constraintName=fk_lesson_course_id, referencedTableName=course','',NULL,'3.5.3',NULL,NULL,'8251990398'),('20180210073801-2','jhipster','config/liquibase/changelog/20180210073801_added_entity_constraints_Feedback.xml','2018-02-10 15:39:50',22,'EXECUTED','7:091cbd68990807ab9559dc32e541ffc0','addForeignKeyConstraint baseTableName=feedback, constraintName=fk_feedback_question_id, referencedTableName=question; addForeignKeyConstraint baseTableName=feedback, constraintName=fk_feedback_user_id, referencedTableName=user','',NULL,'3.5.3',NULL,NULL,'8251990398'),('20180210073802-2','jhipster','config/liquibase/changelog/20180210073802_added_entity_constraints_Question.xml','2018-02-10 15:39:50',23,'EXECUTED','7:b077b88b1a1d75703556081f8026e1b8','addForeignKeyConstraint baseTableName=question, constraintName=fk_question_sub_lesson_id, referencedTableName=sub_lesson','',NULL,'3.5.3',NULL,NULL,'8251990398'),('20180210073803-2','jhipster','config/liquibase/changelog/20180210073803_added_entity_constraints_Answer.xml','2018-02-10 15:39:50',24,'EXECUTED','7:2083602aa86e1ade89582433cb5b7aad','addForeignKeyConstraint baseTableName=answer, constraintName=fk_answer_question_id, referencedTableName=question; addForeignKeyConstraint baseTableName=answer, constraintName=fk_answer_vocabulary_id, referencedTableName=vocabulary','',NULL,'3.5.3',NULL,NULL,'8251990398'),('20180210073804-2','jhipster','config/liquibase/changelog/20180210073804_added_entity_constraints_UserLog.xml','2018-02-10 15:39:50',25,'EXECUTED','7:8864bb8a47e070208aa8d047a91e1ab2','addForeignKeyConstraint baseTableName=user_log, constraintName=fk_user_log_user_id, referencedTableName=user','',NULL,'3.5.3',NULL,NULL,'8251990398'),('20180210073805-2','jhipster','config/liquibase/changelog/20180210073805_added_entity_constraints_GiftLog.xml','2018-02-10 15:39:51',26,'EXECUTED','7:d15a45a662c489de0c85a73f8a802cf0','addForeignKeyConstraint baseTableName=gift_log, constraintName=fk_gift_log_user_id, referencedTableName=user; addForeignKeyConstraint baseTableName=gift_log, constraintName=fk_gift_log_gift_id, referencedTableName=gift','',NULL,'3.5.3',NULL,NULL,'8251990398'),('20180210073806-2','jhipster','config/liquibase/changelog/20180210073806_added_entity_constraints_CourseLog.xml','2018-02-10 15:39:51',27,'EXECUTED','7:f310887d5201d55a62dc50b23f5added','addForeignKeyConstraint baseTableName=course_log, constraintName=fk_course_log_user_id, referencedTableName=user; addForeignKeyConstraint baseTableName=course_log, constraintName=fk_course_log_course_id, referencedTableName=course','',NULL,'3.5.3',NULL,NULL,'8251990398'),('20180210073807-2','jhipster','config/liquibase/changelog/20180210073807_added_entity_constraints_SubLessonLog.xml','2018-02-10 15:39:51',28,'EXECUTED','7:a8075a1f87d2e5a742fea97566a9332b','addForeignKeyConstraint baseTableName=sub_lesson_log, constraintName=fk_sub_lesson_log_user_id, referencedTableName=user; addForeignKeyConstraint baseTableName=sub_lesson_log, constraintName=fk_sub_lesson_log_sub_lesson_id, referencedTableName=sub...','',NULL,'3.5.3',NULL,NULL,'8251990398'),('20180210073808-2','jhipster','config/liquibase/changelog/20180210073808_added_entity_constraints_LessonLog.xml','2018-02-10 15:39:51',29,'EXECUTED','7:08a6ca87be9924e2200aaaa0355aaad0','addForeignKeyConstraint baseTableName=lesson_log, constraintName=fk_lesson_log_user_id, referencedTableName=user; addForeignKeyConstraint baseTableName=lesson_log, constraintName=fk_lesson_log_lesson_id, referencedTableName=lesson','',NULL,'3.5.3',NULL,NULL,'8251990398');
/*!40000 ALTER TABLE `databasechangelog` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `databasechangeloglock`
--

DROP TABLE IF EXISTS `databasechangeloglock`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `databasechangeloglock` (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `databasechangeloglock`
--

LOCK TABLES `databasechangeloglock` WRITE;
/*!40000 ALTER TABLE `databasechangeloglock` DISABLE KEYS */;
INSERT INTO `databasechangeloglock` VALUES (1,'\0',NULL,NULL);
/*!40000 ALTER TABLE `databasechangeloglock` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feedback` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` timestamp NULL,
  `content` varchar(255) NOT NULL,
  `raw_data` longtext,
  `question_id` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_feedback_question_id` (`question_id`),
  KEY `fk_feedback_user_id` (`user_id`),
  CONSTRAINT `fk_feedback_question_id` FOREIGN KEY (`question_id`) REFERENCES `question` (`id`),
  CONSTRAINT `fk_feedback_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gift`
--

DROP TABLE IF EXISTS `gift`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gift` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `price` int(11) NOT NULL,
  `image` longblob NOT NULL,
  `image_content_type` varchar(255) NOT NULL,
  `contenten` longtext,
  `contentvi` longtext,
  `create_date` timestamp NULL,
  `raw_data` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gift`
--

LOCK TABLES `gift` WRITE;
/*!40000 ALTER TABLE `gift` DISABLE KEYS */;
/*!40000 ALTER TABLE `gift` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `gift_log`
--

DROP TABLE IF EXISTS `gift_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `gift_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` timestamp NULL,
  `raw_data` longtext,
  `user_id` bigint(20) DEFAULT NULL,
  `gift_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_gift_log_user_id` (`user_id`),
  KEY `fk_gift_log_gift_id` (`gift_id`),
  CONSTRAINT `fk_gift_log_gift_id` FOREIGN KEY (`gift_id`) REFERENCES `gift` (`id`),
  CONSTRAINT `fk_gift_log_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gift_log`
--

LOCK TABLES `gift_log` WRITE;
/*!40000 ALTER TABLE `gift_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `gift_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lesson`
--

DROP TABLE IF EXISTS `lesson`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lesson` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` timestamp NULL,
  `activated` bit(1) NOT NULL,
  `title` varchar(255) NOT NULL,
  `jhi_level` int(11) NOT NULL,
  `contenten` longtext,
  `contentvi` longtext,
  `image` longblob NOT NULL,
  `image_content_type` varchar(255) NOT NULL,
  `raw_data` longtext,
  `course_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_lesson_course_id` (`course_id`),
  CONSTRAINT `fk_lesson_course_id` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lesson`
--

LOCK TABLES `lesson` WRITE;
/*!40000 ALTER TABLE `lesson` DISABLE KEYS */;
INSERT INTO `lesson` VALUES (1,NULL,'','Hiragana 1',0,'hiragana 1','hiragana 1','‰PNG\r\n\Z\n\0\0\0\rIHDR\0\0\0U\0\0\0V\0\0\0¾a\Úô\0\0\0sRGB\0®\Î\é\0\0\0gAMA\0\0±üa\0\0\0	pHYs\0\0\Ã\0\0\Ã\Ço¨d\0\0}IDATx^\íœË‹E\ÆıCt\ãÒ…\êÊ•¸u+\âR\\\èFw\n®\Í*BP—ºQP5>…D”ˆ†Q£ñE\Ô‰&Qg\îc¦\ì\ßª\é[s\êvU\ßsª{\ÚûÁ3wº\ëñ\İ\Ó\çœ:U=7¹\rÔ±\Õ\0ƒõòÎ¾ûà·¹{á›™{ò\Ì\Ä=ôé®»\çıwû;;\îÖ·şu·¼y@~\æ3ş\Æ5\\\Ë=\ÜKCA/¢N÷œ{÷\Ò\Ü=}vº\è\æJ0\r\Òm\Ò6}ô…¢¢bQ1YXœ$Š&éƒ¾\è³4\ÌE\Åb^¼0Sµ\È\\\Ò7c(e½¦¢¯ü>Pšhd,Œ\É\Z&¢¾ş\Ó\Üİ½51C26\ÆhUQ/\Ş\Øwœˆ\"+cÖ†š¨¯ş0+€´É˜»&TD%‘|”\È´°–¨$\Ü$\á\Ò \"™‹\Æ\"¢³¨__\Û\ë5M²\"sbnë “¨\çş\ÜswœŸ Ì9vE¶¨|‹cÔ“9vµ\Ø,Qñ7c|\äcd®]|l–¨c\nJ©dÎ¹HuiSW\æ¦[I¢’KıŸ˜³@h•e\ÜQ\\)i\rR—´­¢ö¹– ògOœ™¸ç·§\î™s\Ó\Å\ï\Òu¥ˆ)X)*•©qK\Şöö\ÎB\ÄkS\Ù*~ùgñw®“\î·fJuk¥¨¥\Ëw~6‰Š\âÁ¬M\Ú•b®Ô¨_Ë¨o¾²\'¶QŠm…nQT¶JV\ìs¡•–vh³jkF•ı©1\æ\n*Y)m\Û.ûd¡Q¢¨¥–¢\ÏV=¡•6¿2…\æ\ß,‰F1•-]©m\Ş÷Qşò/´R¬³	‚\Ü]ƒklûû¨\ì•K\rhHj”¾\ßV)á«¿\Ê1´’°$*Î·\Ä\ê)´°lıºü!\êõHúUÊ¿¢•°–Då¸Œt³&y<»\â¥\ï–Å¢­ó•e†À\r”\Ê\Ğ,Ä’¨%*Q\ïU·NU~µ)K¬Ù¯¥\nÖ’¨\ÖQ¿Kp’€%zÿº*%+´¤, •\n·t“&ss\ÒUÀB±\ØU(e­\á\î@-ªu*µ/•€ m¯”o\rS«ZT\ÏJ7h±KÄ\àD›)iŒp,\ÚD»&jQ9•,İ \Å.yi<\ÖT´RP\"oE»&jQ-7õ´”\ÖGŞš\n\ë€nÖ¢ZFş—«üR±\ÕTÖ‹0¨Eµ,õi>ú ÷K²vh\×D-ª\ÕòT;\ê¤Rwš°\ÌĞ®‰ZT^©‘nX—¹ª,·_Ğ®	sQµıiWXúÕ¨¨V?5\Ğ! ¬ri2úø[*\í \Õ–›…\Ñ@e•R\r–@4¥²Jş‡i|\ZŒ&ÿV\Ë\Ô\ß\Î\Ğk\à\ìU;K.S­\n*\'/\Í\İ\ÕI¿\Â\Òÿ?\ÛªhAÅªô\Çd8?ß—°ôKÿ–¢FKVE\êf‹IÁ+»e…¥?\ß÷s_Ú•\0£Ej`‘<|jROşø÷¾›kKûô\Ó\ì÷\ŞmqùÁ’¨VŸÿq\à<Y¿[¹¬“ö›ı}|\Ù\î\Ño\İø³Ú¢nº€&¿½®#.–I;¼¢#õCÿÒ¸4ØºEmu˜\âÎ­ƒ—½bÄ²XyQ}Ju\r^H\î-³I«\nZµ¦\0V\Ç~mO\ÅIK\Äâ¾¿q 4AÀ“\ßù|•ˆ!-TÒ±`•Za-ø6i\âV$G¶¬£†©”\Ç!QU€-+\ÒnG\Z‡¥¨\ï!Šjy\è÷şOvE4‰ V)”gö¡_œ¯å•¥\Å\âb¬E)@yˆ¢\ë)ğu~	«\ÅW.\ÎL}¨g§)<J¼òóXA\×\r`|9¸©}m®õ\Ê(ùr\Zâ’¤§º®\ãúRbz®ır\Z\è\ã5J|\âS\Õòb‰şg>·ö™1ª¼F	6/ü\r\Ô^ø›WÓ•_M÷(qt}¨”*Q«,*°<8T†›z)\È•¢†\Õvˆd®aU?Y¢‚Í¿PjG¶¨€<q\Ì\Â27\æ\ØD|‹ctÌ©«…zt\ào\Æ¼˜K\Zb-Q=Æn\å¦M« \"* 9>Š+/Æœ“Ø§@MTÀ2®\Ï¹”KÆšºôÌª¨TrJÿ§ 2¶”jSW˜ˆ\êA1\×r!—Œ¥­À¬SQ\Û\ì\çô™~\Ñ7cXµ¢	sQ›`K—½ò>\è+¶l‰¢¢z`1—!Ñ´`Ú¢M\Ú.e•z5	7\Å\áYN%“„#>‹\ã•\Z\È\Ï|\Æß¸†k¹‡{5’v-BÔ±a#ª6¢\Z`#ª:œûõË¢\'*\äk\0\0\0\0IEND®B`‚','image/png','{row:1}',1),(2,NULL,'\0','hiragana 2',0,'hiragana 2','hiragana 2','‰PNG\r\n\Z\n\0\0\0\rIHDR\0\0\0U\0\0\0V\0\0\0¾a\Úô\0\0\0sRGB\0®\Î\é\0\0\0gAMA\0\0±üa\0\0\0	pHYs\0\0\Ã\0\0\Ã\Ço¨d\0\0}IDATx^\íœË‹E\ÆıCt\ãÒ…\êÊ•¸u+\âR\\\èFw\n®\Í*BP—ºQP5>…D”ˆ†Q£ñE\Ô‰&Qg\îc¦\ì\ßª\é[s\êvU\ßsª{\ÚûÁ3wº\ëñ\İ\Ó\çœ:U=7¹\rÔ±\Õ\0ƒõòÎ¾ûà·¹{á›™{ò\Ì\Ä=ôé®»\çıwû;;\îÖ·şu·¼y@~\æ3ş\Æ5\\\Ë=\ÜKCA/¢N÷œ{÷\Ò\Ü=}vº\è\æJ0\r\Òm\Ò6}ô…¢¢bQ1YXœ$Š&éƒ¾\è³4\ÌE\Åb^¼0Sµ\È\\\Ò7c(e½¦¢¯ü>Pšhd,Œ\É\Z&¢¾ş\Ó\Üİ½51C26\ÆhUQ/\Ş\Øwœˆ\"+cÖ†š¨¯ş0+€´É˜»&TD%‘|”\È´°–¨$\Ü$\á\Ò \"™‹\Æ\"¢³¨__\Û\ë5M²\"sbnë “¨\çş\ÜswœŸ Ì9vE¶¨|‹cÔ“9vµ\Ø,Qñ7c|\äcd®]|l–¨c\nJ©dÎ¹HuiSW\æ¦[I¢’KıŸ˜³@h•e\ÜQ\\)i\rR—´­¢ö¹– ògOœ™¸ç·§\î™s\Ó\Å\ï\Òu¥ˆ)X)*•©qK\Şöö\ÎB\ÄkS\Ù*~ùgñw®“\î·fJuk¥¨¥\Ëw~6‰Š\âÁ¬M\Ú•b®Ô¨_Ë¨o¾²\'¶QŠm…nQT¶JV\ìs¡•–vh³jkF•ı©1\æ\n*Y)m\Û.ûd¡Q¢¨¥–¢\ÏV=¡•6¿2…\æ\ß,‰F1•-]©m\Ş÷Qşò/´R¬³	‚\Ü]ƒklûû¨\ì•K\rhHj”¾\ßV)á«¿\Ê1´’°$*Î·\Ä\ê)´°lıºü!\êõHúUÊ¿¢•°–Då¸Œt³&y<»\â¥\ï–Å¢­ó•e†À\r”\Ê\Ğ,Ä’¨%*Q\ïU·NU~µ)K¬Ù¯¥\nÖ’¨\ÖQ¿Kp’€%zÿº*%+´¤, •\n·t“&ss\ÒUÀB±\ØU(e­\á\î@-ªu*µ/•€ m¯”o\rS«ZT\ÏJ7h±KÄ\àD›)iŒp,\ÚD»&jQ9•,İ \Å.yi<\ÖT´RP\"oE»&jQ-7õ´”\ÖGŞš\n\ë€nÖ¢ZFş—«üR±\ÕTÖ‹0¨Eµ,õi>ú ÷K²vh\×D-ª\ÕòT;\ê¤Rwš°\ÌĞ®‰ZT^©‘nX—¹ª,·_Ğ®	sQµıiWXúÕ¨¨V?5\Ğ! ¬ri2úø[*\í \Õ–›…\Ñ@e•R\r–@4¥²Jş‡i|\ZŒ&ÿV\Ë\Ô\ß\Î\Ğk\à\ìU;K.S­\n*\'/\Í\İ\ÕI¿\Â\Òÿ?\ÛªhAÅªô\Çd8?ß—°ôKÿ–¢FKVE\êf‹IÁ+»e…¥?\ß÷s_Ú•\0£Ej`‘<|jROşø÷¾›kKûô\Ó\ì÷\ŞmqùÁ’¨VŸÿq\à<Y¿[¹¬“ö›ı}|\Ù\î\Ño\İø³Ú¢nº€&¿½®#.–I;¼¢#õCÿÒ¸4ØºEmu˜\âÎ­ƒ—½bÄ²XyQ}Ju\r^H\î-³I«\nZµ¦\0V\Ç~mO\ÅIK\Äâ¾¿q 4AÀ“\ßù|•ˆ!-TÒ±`•Za-ø6i\âV$G¶¬£†©”\Ç!QU€-+\ÒnG\Z‡¥¨\ï!Šjy\è÷şOvE4‰ V)”gö¡_œ¯å•¥\Å\âb¬E)@yˆ¢\ë)ğu~	«\ÅW.\ÎL}¨g§)<J¼òóXA\×\r`|9¸©}m®õ\Ê(ùr\Zâ’¤§º®\ãúRbz®ır\Z\è\ã5J|\âS\Õòb‰şg>·ö™1ª¼F	6/ü\r\Ô^ø›WÓ•_M÷(qt}¨”*Q«,*°<8T†›z)\È•¢†\Õvˆd®aU?Y¢‚Í¿PjG¶¨€<q\Ì\Â27\æ\ØD|‹ctÌ©«…zt\ào\Æ¼˜K\Zb-Q=Æn\å¦M« \"* 9>Š+/Æœ“Ø§@MTÀ2®\Ï¹”KÆšºôÌª¨TrJÿ§ 2¶”jSW˜ˆ\êA1\×r!—Œ¥­À¬SQ\Û\ì\çô™~\Ñ7cXµ¢	sQ›`K—½ò>\è+¶l‰¢¢z`1—!Ñ´`Ú¢M\Ú.e•z5	7\Å\áYN%“„#>‹\ã•\Z\È\Ï|\Æß¸†k¹‡{5’v-BÔ±a#ª6¢\Z`#ª:œûõË¢\'*\äk\0\0\0\0IEND®B`‚','image/png','{row:2}',1),(3,NULL,'\0','hiragana 3',0,'hiragana 3','hiragana 3','‰PNG\r\n\Z\n\0\0\0\rIHDR\0\0\0U\0\0\0V\0\0\0¾a\Úô\0\0\0sRGB\0®\Î\é\0\0\0gAMA\0\0±üa\0\0\0	pHYs\0\0\Ã\0\0\Ã\Ço¨d\0\0}IDATx^\íœË‹E\ÆıCt\ãÒ…\êÊ•¸u+\âR\\\èFw\n®\Í*BP—ºQP5>…D”ˆ†Q£ñE\Ô‰&Qg\îc¦\ì\ßª\é[s\êvU\ßsª{\ÚûÁ3wº\ëñ\İ\Ó\çœ:U=7¹\rÔ±\Õ\0ƒõòÎ¾ûà·¹{á›™{ò\Ì\Ä=ôé®»\çıwû;;\îÖ·şu·¼y@~\æ3ş\Æ5\\\Ë=\ÜKCA/¢N÷œ{÷\Ò\Ü=}vº\è\æJ0\r\Òm\Ò6}ô…¢¢bQ1YXœ$Š&éƒ¾\è³4\ÌE\Åb^¼0Sµ\È\\\Ò7c(e½¦¢¯ü>Pšhd,Œ\É\Z&¢¾ş\Ó\Üİ½51C26\ÆhUQ/\Ş\Øwœˆ\"+cÖ†š¨¯ş0+€´É˜»&TD%‘|”\È´°–¨$\Ü$\á\Ò \"™‹\Æ\"¢³¨__\Û\ë5M²\"sbnë “¨\çş\ÜswœŸ Ì9vE¶¨|‹cÔ“9vµ\Ø,Qñ7c|\äcd®]|l–¨c\nJ©dÎ¹HuiSW\æ¦[I¢’KıŸ˜³@h•e\ÜQ\\)i\rR—´­¢ö¹– ògOœ™¸ç·§\î™s\Ó\Å\ï\Òu¥ˆ)X)*•©qK\Şöö\ÎB\ÄkS\Ù*~ùgñw®“\î·fJuk¥¨¥\Ëw~6‰Š\âÁ¬M\Ú•b®Ô¨_Ë¨o¾²\'¶QŠm…nQT¶JV\ìs¡•–vh³jkF•ı©1\æ\n*Y)m\Û.ûd¡Q¢¨¥–¢\ÏV=¡•6¿2…\æ\ß,‰F1•-]©m\Ş÷Qşò/´R¬³	‚\Ü]ƒklûû¨\ì•K\rhHj”¾\ßV)á«¿\Ê1´’°$*Î·\Ä\ê)´°lıºü!\êõHúUÊ¿¢•°–Då¸Œt³&y<»\â¥\ï–Å¢­ó•e†À\r”\Ê\Ğ,Ä’¨%*Q\ïU·NU~µ)K¬Ù¯¥\nÖ’¨\ÖQ¿Kp’€%zÿº*%+´¤, •\n·t“&ss\ÒUÀB±\ØU(e­\á\î@-ªu*µ/•€ m¯”o\rS«ZT\ÏJ7h±KÄ\àD›)iŒp,\ÚD»&jQ9•,İ \Å.yi<\ÖT´RP\"oE»&jQ-7õ´”\ÖGŞš\n\ë€nÖ¢ZFş—«üR±\ÕTÖ‹0¨Eµ,õi>ú ÷K²vh\×D-ª\ÕòT;\ê¤Rwš°\ÌĞ®‰ZT^©‘nX—¹ª,·_Ğ®	sQµıiWXúÕ¨¨V?5\Ğ! ¬ri2úø[*\í \Õ–›…\Ñ@e•R\r–@4¥²Jş‡i|\ZŒ&ÿV\Ë\Ô\ß\Î\Ğk\à\ìU;K.S­\n*\'/\Í\İ\ÕI¿\Â\Òÿ?\ÛªhAÅªô\Çd8?ß—°ôKÿ–¢FKVE\êf‹IÁ+»e…¥?\ß÷s_Ú•\0£Ej`‘<|jROşø÷¾›kKûô\Ó\ì÷\ŞmqùÁ’¨VŸÿq\à<Y¿[¹¬“ö›ı}|\Ù\î\Ño\İø³Ú¢nº€&¿½®#.–I;¼¢#õCÿÒ¸4ØºEmu˜\âÎ­ƒ—½bÄ²XyQ}Ju\r^H\î-³I«\nZµ¦\0V\Ç~mO\ÅIK\Äâ¾¿q 4AÀ“\ßù|•ˆ!-TÒ±`•Za-ø6i\âV$G¶¬£†©”\Ç!QU€-+\ÒnG\Z‡¥¨\ï!Šjy\è÷şOvE4‰ V)”gö¡_œ¯å•¥\Å\âb¬E)@yˆ¢\ë)ğu~	«\ÅW.\ÎL}¨g§)<J¼òóXA\×\r`|9¸©}m®õ\Ê(ùr\Zâ’¤§º®\ãúRbz®ır\Z\è\ã5J|\âS\Õòb‰şg>·ö™1ª¼F	6/ü\r\Ô^ø›WÓ•_M÷(qt}¨”*Q«,*°<8T†›z)\È•¢†\Õvˆd®aU?Y¢‚Í¿PjG¶¨€<q\Ì\Â27\æ\ØD|‹ctÌ©«…zt\ào\Æ¼˜K\Zb-Q=Æn\å¦M« \"* 9>Š+/Æœ“Ø§@MTÀ2®\Ï¹”KÆšºôÌª¨TrJÿ§ 2¶”jSW˜ˆ\êA1\×r!—Œ¥­À¬SQ\Û\ì\çô™~\Ñ7cXµ¢	sQ›`K—½ò>\è+¶l‰¢¢z`1—!Ñ´`Ú¢M\Ú.e•z5	7\Å\áYN%“„#>‹\ã•\Z\È\Ï|\Æß¸†k¹‡{5’v-BÔ±a#ª6¢\Z`#ª:œûõË¢\'*\äk\0\0\0\0IEND®B`‚','image/png','{row:2}',1),(4,NULL,'\0','hiragana 4',0,'hiragana 4','hiragana 4','‰PNG\r\n\Z\n\0\0\0\rIHDR\0\0\0U\0\0\0V\0\0\0¾a\Úô\0\0\0sRGB\0®\Î\é\0\0\0gAMA\0\0±üa\0\0\0	pHYs\0\0\Ã\0\0\Ã\Ço¨d\0\0}IDATx^\íœË‹E\ÆıCt\ãÒ…\êÊ•¸u+\âR\\\èFw\n®\Í*BP—ºQP5>…D”ˆ†Q£ñE\Ô‰&Qg\îc¦\ì\ßª\é[s\êvU\ßsª{\ÚûÁ3wº\ëñ\İ\Ó\çœ:U=7¹\rÔ±\Õ\0ƒõòÎ¾ûà·¹{á›™{ò\Ì\Ä=ôé®»\çıwû;;\îÖ·şu·¼y@~\æ3ş\Æ5\\\Ë=\ÜKCA/¢N÷œ{÷\Ò\Ü=}vº\è\æJ0\r\Òm\Ò6}ô…¢¢bQ1YXœ$Š&éƒ¾\è³4\ÌE\Åb^¼0Sµ\È\\\Ò7c(e½¦¢¯ü>Pšhd,Œ\É\Z&¢¾ş\Ó\Üİ½51C26\ÆhUQ/\Ş\Øwœˆ\"+cÖ†š¨¯ş0+€´É˜»&TD%‘|”\È´°–¨$\Ü$\á\Ò \"™‹\Æ\"¢³¨__\Û\ë5M²\"sbnë “¨\çş\ÜswœŸ Ì9vE¶¨|‹cÔ“9vµ\Ø,Qñ7c|\äcd®]|l–¨c\nJ©dÎ¹HuiSW\æ¦[I¢’KıŸ˜³@h•e\ÜQ\\)i\rR—´­¢ö¹– ògOœ™¸ç·§\î™s\Ó\Å\ï\Òu¥ˆ)X)*•©qK\Şöö\ÎB\ÄkS\Ù*~ùgñw®“\î·fJuk¥¨¥\Ëw~6‰Š\âÁ¬M\Ú•b®Ô¨_Ë¨o¾²\'¶QŠm…nQT¶JV\ìs¡•–vh³jkF•ı©1\æ\n*Y)m\Û.ûd¡Q¢¨¥–¢\ÏV=¡•6¿2…\æ\ß,‰F1•-]©m\Ş÷Qşò/´R¬³	‚\Ü]ƒklûû¨\ì•K\rhHj”¾\ßV)á«¿\Ê1´’°$*Î·\Ä\ê)´°lıºü!\êõHúUÊ¿¢•°–Då¸Œt³&y<»\â¥\ï–Å¢­ó•e†À\r”\Ê\Ğ,Ä’¨%*Q\ïU·NU~µ)K¬Ù¯¥\nÖ’¨\ÖQ¿Kp’€%zÿº*%+´¤, •\n·t“&ss\ÒUÀB±\ØU(e­\á\î@-ªu*µ/•€ m¯”o\rS«ZT\ÏJ7h±KÄ\àD›)iŒp,\ÚD»&jQ9•,İ \Å.yi<\ÖT´RP\"oE»&jQ-7õ´”\ÖGŞš\n\ë€nÖ¢ZFş—«üR±\ÕTÖ‹0¨Eµ,õi>ú ÷K²vh\×D-ª\ÕòT;\ê¤Rwš°\ÌĞ®‰ZT^©‘nX—¹ª,·_Ğ®	sQµıiWXúÕ¨¨V?5\Ğ! ¬ri2úø[*\í \Õ–›…\Ñ@e•R\r–@4¥²Jş‡i|\ZŒ&ÿV\Ë\Ô\ß\Î\Ğk\à\ìU;K.S­\n*\'/\Í\İ\ÕI¿\Â\Òÿ?\ÛªhAÅªô\Çd8?ß—°ôKÿ–¢FKVE\êf‹IÁ+»e…¥?\ß÷s_Ú•\0£Ej`‘<|jROşø÷¾›kKûô\Ó\ì÷\ŞmqùÁ’¨VŸÿq\à<Y¿[¹¬“ö›ı}|\Ù\î\Ño\İø³Ú¢nº€&¿½®#.–I;¼¢#õCÿÒ¸4ØºEmu˜\âÎ­ƒ—½bÄ²XyQ}Ju\r^H\î-³I«\nZµ¦\0V\Ç~mO\ÅIK\Äâ¾¿q 4AÀ“\ßù|•ˆ!-TÒ±`•Za-ø6i\âV$G¶¬£†©”\Ç!QU€-+\ÒnG\Z‡¥¨\ï!Šjy\è÷şOvE4‰ V)”gö¡_œ¯å•¥\Å\âb¬E)@yˆ¢\ë)ğu~	«\ÅW.\ÎL}¨g§)<J¼òóXA\×\r`|9¸©}m®õ\Ê(ùr\Zâ’¤§º®\ãúRbz®ır\Z\è\ã5J|\âS\Õòb‰şg>·ö™1ª¼F	6/ü\r\Ô^ø›WÓ•_M÷(qt}¨”*Q«,*°<8T†›z)\È•¢†\Õvˆd®aU?Y¢‚Í¿PjG¶¨€<q\Ì\Â27\æ\ØD|‹ctÌ©«…zt\ào\Æ¼˜K\Zb-Q=Æn\å¦M« \"* 9>Š+/Æœ“Ø§@MTÀ2®\Ï¹”KÆšºôÌª¨TrJÿ§ 2¶”jSW˜ˆ\êA1\×r!—Œ¥­À¬SQ\Û\ì\çô™~\Ñ7cXµ¢	sQ›`K—½ò>\è+¶l‰¢¢z`1—!Ñ´`Ú¢M\Ú.e•z5	7\Å\áYN%“„#>‹\ã•\Z\È\Ï|\Æß¸†k¹‡{5’v-BÔ±a#ª6¢\Z`#ª:œûõË¢\'*\äk\0\0\0\0IEND®B`‚','image/png','{row:3}',1),(5,NULL,'\0','hiragana 5',0,'hiragana 5','hiragana 5','‰PNG\r\n\Z\n\0\0\0\rIHDR\0\0\0U\0\0\0V\0\0\0¾a\Úô\0\0\0sRGB\0®\Î\é\0\0\0gAMA\0\0±üa\0\0\0	pHYs\0\0\Ã\0\0\Ã\Ço¨d\0\0}IDATx^\íœË‹E\ÆıCt\ãÒ…\êÊ•¸u+\âR\\\èFw\n®\Í*BP—ºQP5>…D”ˆ†Q£ñE\Ô‰&Qg\îc¦\ì\ßª\é[s\êvU\ßsª{\ÚûÁ3wº\ëñ\İ\Ó\çœ:U=7¹\rÔ±\Õ\0ƒõòÎ¾ûà·¹{á›™{ò\Ì\Ä=ôé®»\çıwû;;\îÖ·şu·¼y@~\æ3ş\Æ5\\\Ë=\ÜKCA/¢N÷œ{÷\Ò\Ü=}vº\è\æJ0\r\Òm\Ò6}ô…¢¢bQ1YXœ$Š&éƒ¾\è³4\ÌE\Åb^¼0Sµ\È\\\Ò7c(e½¦¢¯ü>Pšhd,Œ\É\Z&¢¾ş\Ó\Üİ½51C26\ÆhUQ/\Ş\Øwœˆ\"+cÖ†š¨¯ş0+€´É˜»&TD%‘|”\È´°–¨$\Ü$\á\Ò \"™‹\Æ\"¢³¨__\Û\ë5M²\"sbnë “¨\çş\ÜswœŸ Ì9vE¶¨|‹cÔ“9vµ\Ø,Qñ7c|\äcd®]|l–¨c\nJ©dÎ¹HuiSW\æ¦[I¢’KıŸ˜³@h•e\ÜQ\\)i\rR—´­¢ö¹– ògOœ™¸ç·§\î™s\Ó\Å\ï\Òu¥ˆ)X)*•©qK\Şöö\ÎB\ÄkS\Ù*~ùgñw®“\î·fJuk¥¨¥\Ëw~6‰Š\âÁ¬M\Ú•b®Ô¨_Ë¨o¾²\'¶QŠm…nQT¶JV\ìs¡•–vh³jkF•ı©1\æ\n*Y)m\Û.ûd¡Q¢¨¥–¢\ÏV=¡•6¿2…\æ\ß,‰F1•-]©m\Ş÷Qşò/´R¬³	‚\Ü]ƒklûû¨\ì•K\rhHj”¾\ßV)á«¿\Ê1´’°$*Î·\Ä\ê)´°lıºü!\êõHúUÊ¿¢•°–Då¸Œt³&y<»\â¥\ï–Å¢­ó•e†À\r”\Ê\Ğ,Ä’¨%*Q\ïU·NU~µ)K¬Ù¯¥\nÖ’¨\ÖQ¿Kp’€%zÿº*%+´¤, •\n·t“&ss\ÒUÀB±\ØU(e­\á\î@-ªu*µ/•€ m¯”o\rS«ZT\ÏJ7h±KÄ\àD›)iŒp,\ÚD»&jQ9•,İ \Å.yi<\ÖT´RP\"oE»&jQ-7õ´”\ÖGŞš\n\ë€nÖ¢ZFş—«üR±\ÕTÖ‹0¨Eµ,õi>ú ÷K²vh\×D-ª\ÕòT;\ê¤Rwš°\ÌĞ®‰ZT^©‘nX—¹ª,·_Ğ®	sQµıiWXúÕ¨¨V?5\Ğ! ¬ri2úø[*\í \Õ–›…\Ñ@e•R\r–@4¥²Jş‡i|\ZŒ&ÿV\Ë\Ô\ß\Î\Ğk\à\ìU;K.S­\n*\'/\Í\İ\ÕI¿\Â\Òÿ?\ÛªhAÅªô\Çd8?ß—°ôKÿ–¢FKVE\êf‹IÁ+»e…¥?\ß÷s_Ú•\0£Ej`‘<|jROşø÷¾›kKûô\Ó\ì÷\ŞmqùÁ’¨VŸÿq\à<Y¿[¹¬“ö›ı}|\Ù\î\Ño\İø³Ú¢nº€&¿½®#.–I;¼¢#õCÿÒ¸4ØºEmu˜\âÎ­ƒ—½bÄ²XyQ}Ju\r^H\î-³I«\nZµ¦\0V\Ç~mO\ÅIK\Äâ¾¿q 4AÀ“\ßù|•ˆ!-TÒ±`•Za-ø6i\âV$G¶¬£†©”\Ç!QU€-+\ÒnG\Z‡¥¨\ï!Šjy\è÷şOvE4‰ V)”gö¡_œ¯å•¥\Å\âb¬E)@yˆ¢\ë)ğu~	«\ÅW.\ÎL}¨g§)<J¼òóXA\×\r`|9¸©}m®õ\Ê(ùr\Zâ’¤§º®\ãúRbz®ır\Z\è\ã5J|\âS\Õòb‰şg>·ö™1ª¼F	6/ü\r\Ô^ø›WÓ•_M÷(qt}¨”*Q«,*°<8T†›z)\È•¢†\Õvˆd®aU?Y¢‚Í¿PjG¶¨€<q\Ì\Â27\æ\ØD|‹ctÌ©«…zt\ào\Æ¼˜K\Zb-Q=Æn\å¦M« \"* 9>Š+/Æœ“Ø§@MTÀ2®\Ï¹”KÆšºôÌª¨TrJÿ§ 2¶”jSW˜ˆ\êA1\×r!—Œ¥­À¬SQ\Û\ì\çô™~\Ñ7cXµ¢	sQ›`K—½ò>\è+¶l‰¢¢z`1—!Ñ´`Ú¢M\Ú.e•z5	7\Å\áYN%“„#>‹\ã•\Z\È\Ï|\Æß¸†k¹‡{5’v-BÔ±a#ª6¢\Z`#ª:œûõË¢\'*\äk\0\0\0\0IEND®B`‚','image/png','{row:3}',1),(6,NULL,'\0','hiragana 6',0,'hiragana 6','hiragana 6','‰PNG\r\n\Z\n\0\0\0\rIHDR\0\0\0U\0\0\0V\0\0\0¾a\Úô\0\0\0sRGB\0®\Î\é\0\0\0gAMA\0\0±üa\0\0\0	pHYs\0\0\Ã\0\0\Ã\Ço¨d\0\0}IDATx^\íœË‹E\ÆıCt\ãÒ…\êÊ•¸u+\âR\\\èFw\n®\Í*BP—ºQP5>…D”ˆ†Q£ñE\Ô‰&Qg\îc¦\ì\ßª\é[s\êvU\ßsª{\ÚûÁ3wº\ëñ\İ\Ó\çœ:U=7¹\rÔ±\Õ\0ƒõòÎ¾ûà·¹{á›™{ò\Ì\Ä=ôé®»\çıwû;;\îÖ·şu·¼y@~\æ3ş\Æ5\\\Ë=\ÜKCA/¢N÷œ{÷\Ò\Ü=}vº\è\æJ0\r\Òm\Ò6}ô…¢¢bQ1YXœ$Š&éƒ¾\è³4\ÌE\Åb^¼0Sµ\È\\\Ò7c(e½¦¢¯ü>Pšhd,Œ\É\Z&¢¾ş\Ó\Üİ½51C26\ÆhUQ/\Ş\Øwœˆ\"+cÖ†š¨¯ş0+€´É˜»&TD%‘|”\È´°–¨$\Ü$\á\Ò \"™‹\Æ\"¢³¨__\Û\ë5M²\"sbnë “¨\çş\ÜswœŸ Ì9vE¶¨|‹cÔ“9vµ\Ø,Qñ7c|\äcd®]|l–¨c\nJ©dÎ¹HuiSW\æ¦[I¢’KıŸ˜³@h•e\ÜQ\\)i\rR—´­¢ö¹– ògOœ™¸ç·§\î™s\Ó\Å\ï\Òu¥ˆ)X)*•©qK\Şöö\ÎB\ÄkS\Ù*~ùgñw®“\î·fJuk¥¨¥\Ëw~6‰Š\âÁ¬M\Ú•b®Ô¨_Ë¨o¾²\'¶QŠm…nQT¶JV\ìs¡•–vh³jkF•ı©1\æ\n*Y)m\Û.ûd¡Q¢¨¥–¢\ÏV=¡•6¿2…\æ\ß,‰F1•-]©m\Ş÷Qşò/´R¬³	‚\Ü]ƒklûû¨\ì•K\rhHj”¾\ßV)á«¿\Ê1´’°$*Î·\Ä\ê)´°lıºü!\êõHúUÊ¿¢•°–Då¸Œt³&y<»\â¥\ï–Å¢­ó•e†À\r”\Ê\Ğ,Ä’¨%*Q\ïU·NU~µ)K¬Ù¯¥\nÖ’¨\ÖQ¿Kp’€%zÿº*%+´¤, •\n·t“&ss\ÒUÀB±\ØU(e­\á\î@-ªu*µ/•€ m¯”o\rS«ZT\ÏJ7h±KÄ\àD›)iŒp,\ÚD»&jQ9•,İ \Å.yi<\ÖT´RP\"oE»&jQ-7õ´”\ÖGŞš\n\ë€nÖ¢ZFş—«üR±\ÕTÖ‹0¨Eµ,õi>ú ÷K²vh\×D-ª\ÕòT;\ê¤Rwš°\ÌĞ®‰ZT^©‘nX—¹ª,·_Ğ®	sQµıiWXúÕ¨¨V?5\Ğ! ¬ri2úø[*\í \Õ–›…\Ñ@e•R\r–@4¥²Jş‡i|\ZŒ&ÿV\Ë\Ô\ß\Î\Ğk\à\ìU;K.S­\n*\'/\Í\İ\ÕI¿\Â\Òÿ?\ÛªhAÅªô\Çd8?ß—°ôKÿ–¢FKVE\êf‹IÁ+»e…¥?\ß÷s_Ú•\0£Ej`‘<|jROşø÷¾›kKûô\Ó\ì÷\ŞmqùÁ’¨VŸÿq\à<Y¿[¹¬“ö›ı}|\Ù\î\Ño\İø³Ú¢nº€&¿½®#.–I;¼¢#õCÿÒ¸4ØºEmu˜\âÎ­ƒ—½bÄ²XyQ}Ju\r^H\î-³I«\nZµ¦\0V\Ç~mO\ÅIK\Äâ¾¿q 4AÀ“\ßù|•ˆ!-TÒ±`•Za-ø6i\âV$G¶¬£†©”\Ç!QU€-+\ÒnG\Z‡¥¨\ï!Šjy\è÷şOvE4‰ V)”gö¡_œ¯å•¥\Å\âb¬E)@yˆ¢\ë)ğu~	«\ÅW.\ÎL}¨g§)<J¼òóXA\×\r`|9¸©}m®õ\Ê(ùr\Zâ’¤§º®\ãúRbz®ır\Z\è\ã5J|\âS\Õòb‰şg>·ö™1ª¼F	6/ü\r\Ô^ø›WÓ•_M÷(qt}¨”*Q«,*°<8T†›z)\È•¢†\Õvˆd®aU?Y¢‚Í¿PjG¶¨€<q\Ì\Â27\æ\ØD|‹ctÌ©«…zt\ào\Æ¼˜K\Zb-Q=Æn\å¦M« \"* 9>Š+/Æœ“Ø§@MTÀ2®\Ï¹”KÆšºôÌª¨TrJÿ§ 2¶”jSW˜ˆ\êA1\×r!—Œ¥­À¬SQ\Û\ì\çô™~\Ñ7cXµ¢	sQ›`K—½ò>\è+¶l‰¢¢z`1—!Ñ´`Ú¢M\Ú.e•z5	7\Å\áYN%“„#>‹\ã•\Z\È\Ï|\Æß¸†k¹‡{5’v-BÔ±a#ª6¢\Z`#ª:œûõË¢\'*\äk\0\0\0\0IEND®B`‚','image/png','{row:3}',1);
/*!40000 ALTER TABLE `lesson` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lesson_log`
--

DROP TABLE IF EXISTS `lesson_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lesson_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `jhi_complete` bit(1) DEFAULT NULL,
  `raw_data` longtext,
  `user_id` bigint(20) DEFAULT NULL,
  `lesson_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_lesson_log_user_id` (`user_id`),
  KEY `fk_lesson_log_lesson_id` (`lesson_id`),
  CONSTRAINT `fk_lesson_log_lesson_id` FOREIGN KEY (`lesson_id`) REFERENCES `lesson` (`id`),
  CONSTRAINT `fk_lesson_log_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lesson_log`
--

LOCK TABLES `lesson_log` WRITE;
/*!40000 ALTER TABLE `lesson_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `lesson_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persistent_audit_event`
--

DROP TABLE IF EXISTS `persistent_audit_event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persistent_audit_event` (
  `event_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `principal` varchar(100) NOT NULL,
  `event_date` timestamp NULL DEFAULT NULL,
  `event_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`event_id`),
  KEY `idx_persistent_audit_event` (`principal`,`event_date`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persistent_audit_event`
--

LOCK TABLES `persistent_audit_event` WRITE;
/*!40000 ALTER TABLE `persistent_audit_event` DISABLE KEYS */;
INSERT INTO `persistent_audit_event` VALUES (1,'admin','2018-02-10 08:44:17','AUTHENTICATION_SUCCESS'),(3,'luantm96','2018-02-10 08:48:11','AUTHENTICATION_SUCCESS'),(4,'admin','2018-02-10 08:50:14','AUTHENTICATION_SUCCESS'),(5,'admin','2018-02-11 06:45:59','AUTHENTICATION_SUCCESS'),(6,'admin','2018-02-11 09:15:48','AUTHENTICATION_SUCCESS'),(7,'user','2018-02-11 09:15:58','AUTHENTICATION_SUCCESS'),(8,'admin','2018-02-11 09:17:14','AUTHENTICATION_SUCCESS'),(9,'admin','2018-02-11 09:26:51','AUTHENTICATION_SUCCESS'),(10,'admin','2018-02-11 10:05:02','AUTHENTICATION_SUCCESS'),(11,'admin','2018-02-11 14:30:21','AUTHENTICATION_SUCCESS'),(12,'admin','2018-02-11 14:55:49','AUTHENTICATION_SUCCESS'),(13,'admin','2018-02-11 15:53:30','AUTHENTICATION_SUCCESS'),(14,'admin','2018-02-12 03:47:58','AUTHENTICATION_SUCCESS');
/*!40000 ALTER TABLE `persistent_audit_event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `persistent_audit_evt_data`
--

DROP TABLE IF EXISTS `persistent_audit_evt_data`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `persistent_audit_evt_data` (
  `event_id` bigint(20) NOT NULL,
  `name` varchar(150) NOT NULL,
  `value` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`event_id`,`name`),
  KEY `idx_persistent_audit_evt_data` (`event_id`),
  CONSTRAINT `fk_evt_pers_audit_evt_data` FOREIGN KEY (`event_id`) REFERENCES `persistent_audit_event` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `persistent_audit_evt_data`
--

LOCK TABLES `persistent_audit_evt_data` WRITE;
/*!40000 ALTER TABLE `persistent_audit_evt_data` DISABLE KEYS */;
/*!40000 ALTER TABLE `persistent_audit_evt_data` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `post` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `contenten` longtext,
  `contentvi` longtext,
  `create_date` timestamp NULL,
  `last_modifier` timestamp NULL,
  `activated` bit(1) NOT NULL,
  `raw_data` longtext,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_post_user_id` (`user_id`),
  CONSTRAINT `fk_post_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (1,'[HÆ°á»›ng dáº«n] TÃ”I NÃŠN ÄÄ‚NG BÃ€I ÄÄ‚NG Cá»¦A MÃŒNH TRÃŠN DIá»„N ÄÃ€N NÃ€O ?','Náº¿u báº¡n lÃ m má»™t thÃ nh viÃªn má»›i hoáº·c Ä‘Æ¡n giáº£n báº¡n váº«n chÆ°a biáº¿t nÃªn Ä‘Äƒng bÃ i cá»§a mÃ¬nh á»Ÿ Ä‘Ã¢u lÃ  phÃ¹ há»£p. BÃ i Ä‘Äƒng nÃ y sáº½ giÃºp báº¡n. TrÆ°á»›c khi táº¡o má»™t bÃ i Ä‘Äƒng, hÃ£y cháº¯c ráº±ng báº¡n khÃ´ng tháº¥y má»™t bÃ i','Náº¿u báº¡n lÃ m má»™t thÃ nh viÃªn má»›i hoáº·c Ä‘Æ¡n giáº£n báº¡n váº«n chÆ°a biáº¿t nÃªn Ä‘Äƒng bÃ i cá»§a mÃ¬nh á»Ÿ Ä‘Ã¢u lÃ  phÃ¹ há»£p. BÃ i Ä‘Äƒng nÃ y sáº½ giÃºp báº¡n. TrÆ°á»›c khi táº¡o má»™t bÃ i Ä‘Äƒng, hÃ£y cháº¯c ráº±ng báº¡n khÃ´ng tháº¥y má»™t bÃ i','2018-02-11 10:11:40','2018-02-11 14:56:14','','5',NULL),(2,'Language guides to help with learning Japanese','These posts are designed to support your Japanese learning and to help you become familiar with the language. You can also ask each other some questions or share your language learning experience in the Duolingo community by leaving your comments. Remember to check the list regularly for new content and share these with friends who have an interest in learning Japanese!','These posts are designed to support your Japanese learning and to help you become familiar with the language. You can also ask each other some questions or share your language learning experience in the Duolingo community by leaving your comments. Remember to check the list regularly for new content and share these with friends who have an interest in learning Japanese!','2018-02-11 14:57:04',NULL,'','4',NULL),(3,' Tuyá»‡t chiÃªu giÃºp báº¡n luyá»‡n viáº¿t Tiáº¿ng Nháº­t hiá»‡u quáº£','in english: Báº¡n cáº£m tháº¥y sá»£ vÃ  ngáº¡i viáº¿t tiáº¿ng anh. ÄÃ¢y cÅ©ng lÃ  trá»Ÿ ngáº¡i cá»§a báº¥t kÃ¬ báº¡n nÃ o khi há»c tiáº¿ng anh. MÃ¬nh xin gá»­i táº·ng cÃ¡c báº¡n bá»™ \"bÃ­ kÃ­p\" luyá»‡n viáº¿t dÆ°á»›i Ä‘Ã¢y Ä‘á»ƒ giÃºp cÃ¡c báº¡n há»c viáº¿t Ä‘Æ°á»£c hiá»‡u quáº£ hÆ¡n nhÃ©.','in vietnamese: Báº¡n cáº£m tháº¥y sá»£ vÃ  ngáº¡i viáº¿t tiáº¿ng anh. ÄÃ¢y cÅ©ng lÃ  trá»Ÿ ngáº¡i cá»§a báº¥t kÃ¬ báº¡n nÃ o khi há»c tiáº¿ng anh. MÃ¬nh xin gá»­i táº·ng cÃ¡c báº¡n bá»™ \"bÃ­ kÃ­p\" luyá»‡n viáº¿t dÆ°á»›i Ä‘Ã¢y Ä‘á»ƒ giÃºp cÃ¡c báº¡n há»c viáº¿t Ä‘Æ°á»£c hiá»‡u quáº£ hÆ¡n nhÃ©.','2018-02-11 14:58:09',NULL,'','2',NULL);
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` timestamp NULL,
  `question_type` varchar(255) NOT NULL,
  `question_sub_type` varchar(255) NOT NULL,
  `contenten` varchar(255) NOT NULL,
  `contentvi` varchar(255) NOT NULL,
  `image` longblob,
  `image_content_type` varchar(255) DEFAULT NULL,
  `jhi_resource` longblob,
  `jhi_resource_content_type` varchar(255) DEFAULT NULL,
  `raw_data` longtext,
  `sub_lesson_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_question_sub_lesson_id` (`sub_lesson_id`),
  CONSTRAINT `fk_question_sub_lesson_id` FOREIGN KEY (`sub_lesson_id`) REFERENCES `sub_lesson` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room`
--

DROP TABLE IF EXISTS `room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `room` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` timestamp NULL,
  `jhi_level` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `raw_data` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room`
--

LOCK TABLES `room` WRITE;
/*!40000 ALTER TABLE `room` DISABLE KEYS */;
INSERT INTO `room` VALUES (1,'2018-02-11 15:46:56',0,'Normal 1','bg-aqua'),(2,'2018-02-11 15:47:07',0,'Normal 2','bg-green'),(3,'2018-02-11 15:47:18',5,'Vip 1','bg-yellow'),(4,'2018-02-11 15:47:24',10,'Vip 2','bg-yellow'),(5,'2018-02-11 15:47:31',15,'Vip 3','bg-yellow'),(6,'2018-02-11 15:47:37',20,'Super','bg-red');
/*!40000 ALTER TABLE `room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `social_user_connection`
--

DROP TABLE IF EXISTS `social_user_connection`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `social_user_connection` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) NOT NULL,
  `provider_id` varchar(255) NOT NULL,
  `provider_user_id` varchar(255) NOT NULL,
  `rank` bigint(20) NOT NULL,
  `display_name` varchar(255) DEFAULT NULL,
  `profile_url` varchar(255) DEFAULT NULL,
  `image_url` varchar(255) DEFAULT NULL,
  `access_token` varchar(255) NOT NULL,
  `secret` varchar(255) DEFAULT NULL,
  `refresh_token` varchar(255) DEFAULT NULL,
  `expire_time` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`,`provider_id`,`provider_user_id`),
  UNIQUE KEY `user_id_2` (`user_id`,`provider_id`,`rank`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `social_user_connection`
--

LOCK TABLES `social_user_connection` WRITE;
/*!40000 ALTER TABLE `social_user_connection` DISABLE KEYS */;
/*!40000 ALTER TABLE `social_user_connection` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sub_lesson`
--

DROP TABLE IF EXISTS `sub_lesson`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sub_lesson` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` timestamp NULL,
  `title` varchar(255) NOT NULL,
  `contenten` longtext,
  `contentvi` longtext,
  `raw_data` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sub_lesson`
--

LOCK TABLES `sub_lesson` WRITE;
/*!40000 ALTER TABLE `sub_lesson` DISABLE KEYS */;
/*!40000 ALTER TABLE `sub_lesson` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sub_lesson_log`
--

DROP TABLE IF EXISTS `sub_lesson_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sub_lesson_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `jhi_complete` bit(1) DEFAULT NULL,
  `raw_data` longtext,
  `user_id` bigint(20) DEFAULT NULL,
  `sub_lesson_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_sub_lesson_log_user_id` (`user_id`),
  KEY `fk_sub_lesson_log_sub_lesson_id` (`sub_lesson_id`),
  CONSTRAINT `fk_sub_lesson_log_sub_lesson_id` FOREIGN KEY (`sub_lesson_id`) REFERENCES `sub_lesson` (`id`),
  CONSTRAINT `fk_sub_lesson_log_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sub_lesson_log`
--

LOCK TABLES `sub_lesson_log` WRITE;
/*!40000 ALTER TABLE `sub_lesson_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `sub_lesson_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `login` varchar(100) NOT NULL,
  `password_hash` varchar(60) DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `image_url` varchar(256) DEFAULT NULL,
  `activated` bit(1) NOT NULL,
  `lang_key` varchar(6) DEFAULT NULL,
  `activation_key` varchar(20) DEFAULT NULL,
  `reset_key` varchar(20) DEFAULT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` timestamp NOT NULL,
  `reset_date` timestamp NULL DEFAULT NULL,
  `image` blob,
  `image_content_type` varchar(255) DEFAULT NULL,
  `coin` int(11) NOT NULL,
  `point` int(11) NOT NULL,
  `today_point` int(11) DEFAULT NULL,
  `date_goal` int(11) DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_user_login` (`login`),
  UNIQUE KEY `idx_user_login` (`login`),
  UNIQUE KEY `ux_user_email` (`email`),
  UNIQUE KEY `idx_user_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (1,'system','$2a$10$mE.qmcV0mFU5NcKh73TZx.z4ueI/.bDWbj0T1BYyqP481kGGarKLG','System','System','system@localhost',NULL,'','en',NULL,NULL,'system','2018-02-10 08:37:53',NULL,NULL,NULL,0,0,NULL,NULL,'system',NULL),(2,'anonymoususer','$2a$10$j8S5d7Sr7.8VTOYNviDPOeWX8KcYILUVJBsYV83Y5NtECayypx9lO','Anonymous','User','anonymous@localhost',NULL,'','en',NULL,NULL,'system','2018-02-10 08:37:53',NULL,NULL,NULL,0,0,NULL,NULL,'system',NULL),(3,'admin','$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC','Administrator','Administrator','admin@localhost',NULL,'','en',NULL,NULL,'system','2018-02-10 08:37:53',NULL,NULL,NULL,0,0,NULL,NULL,'system',NULL),(4,'user','$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K','User','User','user@localhost',NULL,'','en',NULL,NULL,'system','2018-02-10 08:37:53',NULL,NULL,NULL,0,0,NULL,NULL,'system',NULL),(5,'luantm96','$2a$10$iQFBWoLGXNZFtMKXwZHNtuznrpDd7MFunI3SOTBLqKCxvK3me/oTq',NULL,NULL,'luantm96@gmail.com',NULL,'','en',NULL,NULL,'anonymousUser','2018-02-10 08:47:30',NULL,NULL,NULL,0,0,NULL,NULL,'anonymousUser','2018-02-10 08:48:04'),(6,'ngoalongtb','$2a$10$yfxUms.AIIRvZnS/SkWQc.dR359ilV.LBjrFPtc9rxdz77awNQxyi','ngoalong','ngoalong','ngoalongtb001@gmail.com',NULL,'','vi',NULL,NULL,'admin','2018-02-10 08:50:47',NULL,NULL,NULL,0,0,NULL,NULL,'anonymousUser','2018-02-10 08:51:24');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_authority`
--

DROP TABLE IF EXISTS `user_authority`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_authority` (
  `user_id` bigint(20) NOT NULL,
  `authority_name` varchar(50) NOT NULL,
  PRIMARY KEY (`user_id`,`authority_name`),
  KEY `fk_authority_name` (`authority_name`),
  CONSTRAINT `fk_authority_name` FOREIGN KEY (`authority_name`) REFERENCES `authority` (`name`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_authority`
--

LOCK TABLES `user_authority` WRITE;
/*!40000 ALTER TABLE `user_authority` DISABLE KEYS */;
INSERT INTO `user_authority` VALUES (1,'ROLE_ADMIN'),(3,'ROLE_ADMIN'),(6,'ROLE_ADMIN'),(1,'ROLE_USER'),(3,'ROLE_USER'),(4,'ROLE_USER'),(5,'ROLE_USER'),(6,'ROLE_USER'),(6,'ROLL_TEACHER');
/*!40000 ALTER TABLE `user_authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_log`
--

DROP TABLE IF EXISTS `user_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_date` timestamp NULL,
  `jhi_complete` bit(1) DEFAULT NULL,
  `point` int(11) DEFAULT NULL,
  `raw_data` longtext,
  `user_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_log_user_id` (`user_id`),
  CONSTRAINT `fk_user_log_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_log`
--

LOCK TABLES `user_log` WRITE;
/*!40000 ALTER TABLE `user_log` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_log` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `vocabulary`
--

DROP TABLE IF EXISTS `vocabulary`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `vocabulary` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `japanese` varchar(255) NOT NULL,
  `english` varchar(255) NOT NULL,
  `vietnamese` varchar(255) NOT NULL,
  `image` longblob NOT NULL,
  `image_content_type` varchar(255) NOT NULL,
  `audio` longblob,
  `audio_content_type` varchar(255) DEFAULT NULL,
  `raw_data` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `vocabulary`
--

LOCK TABLES `vocabulary` WRITE;
/*!40000 ALTER TABLE `vocabulary` DISABLE KEYS */;
/*!40000 ALTER TABLE `vocabulary` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'hlschool'
--

--
-- Dumping routines for database 'hlschool'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-02-12 11:15:03
