-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
-- Thời gian đã tạo: Th6 17, 2021 lúc 05:10 PM
-- Phiên bản máy phục vụ: 10.4.19-MariaDB
-- Phiên bản PHP: 8.0.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `db_ttcm`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `ctdh`
--

CREATE TABLE `ctdh` (
  `MaDH` int(11) NOT NULL,
  `MaMonAn` int(11) NOT NULL,
  `SL` int(11) NOT NULL,
  `Gia` int(11) NOT NULL,
  `TongTien` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `ctdh`
--

INSERT INTO `ctdh` (`MaDH`, `MaMonAn`, `SL`, `Gia`, `TongTien`) VALUES
(4, 1, 1, 15000, 15000),
(4, 2, 1, 30000, 30000),
(4, 3, 1, 15000, 15000),
(4, 4, 1, 30000, 30000),
(9, 3, 1, 18000, 18000),
(9, 4, 1, 18000, 18000),
(13, 3, 1, 18000, 18000),
(14, 4, 1, 18000, 18000),
(15, 4, 1, 18000, 18000),
(16, 4, 1, 18000, 18000),
(17, 3, 1, 18000, 18000),
(17, 4, 1, 18000, 18000),
(18, 4, 2, 18000, 36000),
(18, 30, 1, 20000, 20000),
(18, 77, 3, 12000, 36000),
(19, 42, 1, 200000, 200000),
(19, 66, 2, 12000, 24000),
(20, 3, 1, 18000, 18000),
(20, 77, 4, 12000, 48000),
(21, 2, 1, 30000, 30000),
(21, 3, 1, 18000, 18000),
(21, 4, 1, 18000, 18000),
(22, 3, 2, 18000, 36000),
(22, 4, 1, 18000, 18000),
(22, 57, 1, 15000, 15000),
(23, 3, 1, 18000, 18000),
(23, 57, 1, 15000, 15000),
(23, 58, 1, 15000, 15000),
(25, 3, 1, 18000, 18000),
(25, 4, 1, 18000, 18000),
(26, 61, 1, 12000, 12000),
(27, 16, 1, 30000, 30000),
(27, 20, 1, 60000, 60000);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `donhang`
--

CREATE TABLE `donhang` (
  `MaDH` int(11) NOT NULL,
  `SDT` varchar(50) NOT NULL,
  `ThanhTien` varchar(50) NOT NULL,
  `ThoiGian` datetime NOT NULL,
  `DiaChi` varchar(250) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `donhang`
--

INSERT INTO `donhang` (`MaDH`, `SDT`, `ThanhTien`, `ThoiGian`, `DiaChi`) VALUES
(4, '0123456789', '15000', '2021-06-06 18:12:14', '448 le van viet'),
(5, '0123456789', '30000', '2021-06-06 20:49:32', '448 le van viet'),
(6, '0123456789', '30000', '2021-06-06 20:49:41', '448 le van viet'),
(7, '0123456789', '30000', '2021-06-06 20:50:10', '448 le van viet'),
(8, '0123456789', '30000', '2021-06-06 20:51:14', '448 le van viet'),
(9, '0123456789', '18000', '2021-06-06 21:09:11', '448 le van viet'),
(10, '0123456789', '18000', '2021-06-06 21:10:22', '448le van viet'),
(11, '0123456789', '18000', '2021-06-06 21:32:58', 'a'),
(12, '0123456789', '23000', '2021-06-06 21:42:59', 'm'),
(13, '0123456789', '18000', '2021-06-06 21:57:51', 'k'),
(14, '0123456789', '18000', '2021-06-06 22:14:25', 'j'),
(15, '0123456789', '18000', '2021-06-06 22:15:20', 'l'),
(16, '0123456789', '18000', '2021-06-06 22:15:59', 'n'),
(17, '0123456789', '36000', '2021-06-07 00:54:04', '448 le van viet'),
(18, '0123456789', '92000', '2021-06-07 08:31:11', 'KTX DH GTVT CSII'),
(19, '0123456789', '224000', '2021-06-07 17:52:47', '3Vo van ngan thu duc'),
(20, '0123456789', '66000', '2021-06-09 17:29:50', 'dd'),
(21, '0123456789', '66000', '2021-06-12 11:33:25', 'q'),
(22, '0123456789', '69000', '2021-06-12 16:10:45', '448 le van viet'),
(23, '0123456789', '48000', '2021-06-13 10:12:39', '448 le van viet'),
(24, '0123456789', '36000', '2021-06-15 18:03:30', '448 le van viet'),
(25, '0123456789', '36000', '2021-06-15 18:03:30', '448 le van viet'),
(26, '0123456789', '18000', '2021-06-15 22:46:40', 'ktx dhgtvt cs2'),
(27, '0123456789', '30000', '2021-06-15 23:44:14', '448 le van viet');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `loaimonan`
--

CREATE TABLE `loaimonan` (
  `MaLoai` int(11) NOT NULL,
  `TenLoai` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `loaimonan`
--

INSERT INTO `loaimonan` (`MaLoai`, `TenLoai`) VALUES
(1, 'Cơm'),
(2, 'Bún'),
(3, 'Bánh'),
(4, 'Đồ ngọt'),
(5, 'Đồ uống'),
(6, 'Đồ nướng'),
(7, 'Lẩu');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `monan`
--

CREATE TABLE `monan` (
  `MaMonAn` int(11) NOT NULL,
  `TenMonAn` varchar(255) NOT NULL,
  `Gia` int(11) NOT NULL,
  `HinhAnh` varchar(500) NOT NULL,
  `MaLoai` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `monan`
--

INSERT INTO `monan` (`MaMonAn`, `TenMonAn`, `Gia`, `HinhAnh`, `MaLoai`) VALUES
(1, 'Cơm Tấm', 23000, 'https://th.bing.com/th/id/R645e9c75227cec18ccc0969e2b40c8d9?rik=SOzMl3JyQ8%2bF1w&riu=http%3a%2f%2fnauankhongkho.com%2fwp-content%2fuploads%2f2017%2f11%2fVVV.jpg&ehk=md8KjmBS%2f6sqz30ag9VG9xIE5GMSxpqVs8hI9Yr5YoI%3d&risl=&pid=ImgRaw', 1),
(2, 'Phở Bò', 30000, 'https://1.bp.blogspot.com/-juinvUI0580/WaezPIn1VzI/AAAAAAAABPU/Ui-8k3YHdv4eKMN8nABb2dRx-C24eYPawCEwYBhgL/s1600/pho%2Bbo.png', 2),
(3, 'Sữa tươi chân châu đường đen', 18000, 'https://th.bing.com/th/id/OIP.VglI0RIVjn_AzquXvSMxegHaE8?pid=ImgDet&rs=1', 5),
(4, 'Trà đào', 18000, 'https://th.bing.com/th/id/R91578ac88e93484c032f0fcb273807b7?rik=LPg7%2ft0iwJWoBw&pid=ImgRaw', 5),
(5, 'Cơm gà xối mỡ', 25000, 'https://kenhphunu.com/media/102015/1464195600/com-ga-xoi-mo.jpg', 1),
(6, 'Cơm gà xé', 25000, 'https://yeutre.vn/cdn/medias/uploads/6/6553-a1-com-ga.jpg', 1),
(7, 'Cơm chiên hải sản', 30000, 'https://product.hstatic.net/1000335798/product/2ba8c45678e99db7c4f8_1cc5e3355efb438fac135d6cb8d43838_1024x1024.jpg', 1),
(8, 'Cá kèo kho tộ', 50000, 'https://th.bing.com/th/id/OIP.0s5go-Q8LsnBfNcwH_zRUwHaE8?pid=ImgDet&rs=1', 1),
(9, 'Cá nục kho măng', 45000, 'https://th.bing.com/th/id/Reb049e438e70d938273c9ec50fcdf386?rik=W4jZtXjXKwOfzw&riu=http%3a%2f%2f4.bp.blogspot.com%2f-a0LdPWO-_Ps%2fUAPIf4TBnmI%2fAAAAAAAAEDo%2fPWtxSt_4_6s%2fs1600%2fcakho8%2b-%2bCopy.jpg&ehk=9WfDH%2b5hrTiltFMC9wW2Liqk1N8HHxRbB5xn%2bHSRKGI%3d&risl=&pid=ImgRaw', 1),
(10, 'Cá lóc kho tộ', 50000, 'https://i.ytimg.com/vi/IUZA7qQ2mBQ/maxresdefault.jpg', 1),
(11, 'Cá basa kho', 35000, 'https://th.bing.com/th/id/R06edc01df41aeeb9f26f99eceadcdd29?rik=b%2fooVvVj4GtNPA&pid=ImgRaw', 1),
(12, 'Cá thu sốt cà', 55000, 'https://th.bing.com/th/id/OIP.vzEP3Gjg6jOUa1LjH__U1QHaFj?pid=ImgDet&rs=1', 1),
(13, 'Cá diêu Hồng chiên', 50000, 'https://th.bing.com/th/id/OIP.CUgqDDSYoqdxxbJKzZWnEwHaEo?pid=ImgDet&rs=1', 1),
(14, 'Cá cơm kho', 45000, 'https://th.bing.com/th/id/OIP.03cn1188wdslgTBUxsFLlgHaHa?pid=ImgDet&rs=1', 1),
(15, 'Cá nục chiên', 45000, 'https://img-global.cpcdn.com/005_recipes/8176e727a9d0cedc/1200x630cq70/photo.jpg', 1),
(16, 'Bún Thái Ngon Muối Ớt Xanh', 30000, 'https://images.foody.vn/res/g104/1034030/prof/s640x400/foody-upload-api-foody-mobile-foody-upload-api-foo-200702134850.jpg', 2),
(17, 'Bún Riêu Cua', 30000, 'https://cdn.tgdd.vn/Files/2018/06/12/1094963/bon-buoc-de-co-ngay-mon-bun-rieu-cua-dong.jpg', 2),
(18, 'Bún Thịt Nướng', 40000, 'https://amazingdalat.com/media/upload/images/am-thuc/Bun%20Thit%20Nuong%20Da%20Lat..jpg', 2),
(19, 'Bánh Canh Cua', 50000, 'https://th.bing.com/th/id/Ra11dc145f5281705e03787cfd2608b68?rik=yqlnN77av2ezjA&pid=ImgRaw', 2),
(20, 'Bún Đậu Mắm Tôm', 60000, 'https://i.ytimg.com/vi/17G58vEKVJE/maxresdefault.jpg', 2),
(21, 'Bún Cá ', 35000, 'https://th.bing.com/th/id/OIP.tbLJmTpEjsUoCFvZvbGxiwHaEK?pid=ImgDet&rs=1', 2),
(22, 'Bún Măng Vịt', 30000, 'https://nghebep.com/wp-content/uploads/2017/12/bun-mang-vit.jpg', 2),
(23, 'Bún Bò Huế Xưa', 50000, 'https://static.riviu.co/960/image/2020/11/05/e68161ad3a40e498239e86f02638bbbb_output.jpeg', 2),
(24, 'Hủ Tiếu Chay', 40000, 'https://img.canhdep.net/image/2016/05/ngay-he-thanh-dam-voi-hu-tieu-chay-sai-thanh.jpg', 2),
(25, 'Hủ Tiếu', 45000, 'https://mustgo.vn/files/uploads/1567007856-maxresdefault.jpg', 2),
(26, 'Bánh Canh Ghẹ Muối Ớt Xanh', 100000, 'https://th.bing.com/th/id/OIP.8QhDtiujhf-gm5IISN9USQHaE7?pid=ImgDet&rs=1', 2),
(27, 'Mì Trộn', 35000, 'https://beptruong.edu.vn/wp-content/uploads/2013/06/mi-tron-han-quoc.jpg', 2),
(28, 'Mì Quảng ', 30000, 'https://wiki-travel.com.vn/Uploads/Picture/anhthuy1202-183015013048-du-lich-quang-nam-mi-quang.jpg', 2),
(29, 'Mì Xào', 50000, 'https://th.bing.com/th/id/Rf16aed5382d13ce0cf5628c1f3b106fa?rik=%2bhhTbLyyKKFv4Q&pid=ImgRaw', 2),
(30, 'Bánh Mì Xíu Mại', 20000, 'https://thumbs.dreamstime.com/b/banh-mi-xiu-mai-vietnamese-sandwich-meatballs-tomato-sauce-do-chua-radish-carrot-pickle-cucumber-coriander-35622531.jpg', 3),
(31, 'Bánh Mì Thịt Nướng', 20000, 'https://th.bing.com/th/id/Rca3a84db523cdfab928db35459694cb6?rik=diJPspPBAe9MPA&pid=ImgRaw', 3),
(32, 'Bánh Mì Trứng', 15000, 'https://th.bing.com/th/id/R4e70042e3ff5ab71d630b75d24c658c0?rik=jU1XEmbBRCdBqg&riu=http%3a%2f%2fgiammoantoan.vn%2fwp-content%2fuploads%2f2019%2f07%2f1-o-banh-mi-trung-bao-nhieu-calo-1.jpg&ehk=P9OKvp3UcW1HwkKHU1N2WcgUfoi2zX3Fa%2b%2bMOGjl5TQ%3d&risl=&pid=ImgRaw', 3),
(33, 'Bánh Mì Chả', 20000, 'https://1.bp.blogspot.com/-tvp2lasfhaM/XprSUaEmihI/AAAAAAAAAhQ/2ppzoOcLoksINuwK5t82VbEVC1N049-XACLcBGAsYHQ/w1200-h630-p-k-no-nu/cach-lam-banh-mi-cha-ca-nha-trang.jpg', 3),
(34, 'Bánh Mì Que', 15000, 'https://th.bing.com/th/id/Rcf2fca93f8c4ca6101fd59330b1ae049?rik=g0r5sbOw6e%2bgWQ&pid=ImgRaw', 3),
(35, 'Bánh Mì Nướng Muối Ớt', 30000, 'https://cdn.daylambanh.edu.vn/wp-content/uploads/2017/08/banh-mi-nuong-moi-ot.jpg', 3),
(36, 'Bánh Bao', 15000, 'https://th.bing.com/th/id/OIP.Ktwow0A4f5Ju4mSmQ7-quAHaGL?pid=ImgDet&rs=1', 3),
(37, 'Nem Cuốn', 25000, 'https://th.bing.com/th/id/Re8df45fa1c62bc98409e64df92eb02bd?rik=MriMZBDbV%2f9AEg&riu=http%3a%2f%2fhealthplus.vn%2fImages%2fUploaded%2fShare%2f2015%2f02%2f05%2fcac-mon-cuon-nem-tom-thit-1.jpg&ehk=v9eACLSiOycatKkWgIiEFxrUEXLtpyMwL9bFATxN8s0%3d&risl=&pid=ImgRaw', 3),
(38, 'Bánh Xèo', 100000, 'https://yesyourtrip.com/wp-content/uploads/2017/04/Banh-xeo-Vietnamese-pancake-1.jpg', 3),
(39, 'Bánh Bèo', 30000, 'https://th.bing.com/th/id/OIP.HoP82hG95jxm4wYAR-9LZgHaFj?pid=ImgDet&rs=1', 3),
(40, 'Bánh Bột Loc', 20000, 'https://s.tinnhanh.ai/uploads/editors/2020/04/22/cong-thuc-lam-banh-bot-loc-dung-chat-xu-hue-va-cau-chuyen-cam-dong-ve-mon-an-tuoi-tho-cua-ma3-1-5e9fb7a7e9d89.jpg', 3),
(41, 'Bánh Ít', 25000, 'https://quynhonme.vn/wp-content/uploads/2020/03/banh-it-la-gai-binh-dinh.jpg', 3),
(42, 'Lẩu Thái', 200000, 'https://image.baophapluat.vn/w800/Uploaded/2020/carwqwrwq/2018_04_17/11_zqqd.jpg', 7),
(43, 'Lẩu Gà Ớt Hiểm', 25000, 'https://th.bing.com/th/id/R9d2908af2f2f731e8f5b8348c49abbb6?rik=NCDoj85vVZ1CpA&pid=ImgRaw', 7),
(44, 'Lẩu Gà Nấm', 25000, 'https://cdn.tgdd.vn/Files/2020/01/09/1230808/huong-dan-cach-nau-lau-ga-nam-ngon-chuan-nha-hang-ca-nha-deu-me-1.jpg', 7),
(45, 'Lẩu Cá Đuối - 2 người ăn', 300000, 'https://th.bing.com/th/id/R23767329f83f9a2d0f865689fd240a0d?rik=fZNYO1ls78C54w&riu=http%3a%2f%2fmonngonmoingay.com%2fwp-content%2fuploads%2f2015%2f08%2fCQC6700_lau-caduoi.png&ehk=ZGkUHEOmBpjEv5%2fVcGsS60xXK0PiuO%2bbjQFzPKawBXM%3d&risl=&pid=ImgRaw', 7),
(46, 'Lẩu Cá Bớp', 200000, 'https://hoanghaigroup.com/wp-content/uploads/2020/08/Cach-lam-lau-chua-ca-bop-thom-ngon-tai-nha7.jpg', 7),
(47, 'Lẩu Hải Sản Chua Cay', 450000, 'https://kienthucmevabe.net/wp-content/uploads/2019/10/lau-hai-san-4.jpg', 7),
(48, 'Lẩu Bò', 350000, 'https://bao.click49.net/wp-content/uploads/2018/04/2018-04-13-12-43-50.jpg', 7),
(49, 'Lẩu mắm', 150000, 'https://mustgo.vn/files/uploads/1566234850-anh-2.jpg', 7),
(50, 'Lẩu gà lá giang', 200000, 'https://th.bing.com/th/id/R1cac6f981e92c9aa4bade2db6b41d62f?rik=RAIshSWCEUnZQw&pid=ImgRaw', 7),
(51, 'Lẩu ghẹ', 500000, 'https://th.bing.com/th/id/OIP.YWIx1WX8FMFD5ljenFZ-BQHaEK?pid=ImgDet&rs=1', 7),
(52, 'Lẩu Cua Đồng', 300000, 'https://thucthan.com/media/2018/07/lau-cua-dong/cach-nau-lau-cua-dong.jpg', 7),
(53, 'Lẩu dê', 200000, 'https://th.bing.com/th/id/Ra563aeaa7ecd6516bd1ac6ce122fcbe0?rik=NB01g10rt6pBPg&pid=ImgRaw', 7),
(54, 'Lẩu chay', 200000, 'https://webnauan.vn/wp-content/uploads/2020/07/cach-nau-lau-thai-chay.jpg', 7),
(55, 'Lẩu bò nhúng giấm', 350000, 'https://th.bing.com/th/id/R7b2b413d6ed90fff83eb5d2d64887133?rik=ZDbjki7SM%2fDv6Q&pid=ImgRaw', 7),
(56, 'Lẩu Vịt Om Sấu', 250000, 'https://lh5.googleusercontent.com/proxy/oMtr7sLz-0ilocIAVywvCAoECVQHpRKsmMqhQbNyRsjnDOMciurlv7ALdQJTlesTDYtyL2UImCXygsXrqCAw9KeKJ4LFVHUoxFbBsumuh9Gxgb-TldUdPgDb=w1200-h630-p-k-no-nu', 7),
(57, 'Cafe Sữa Đá', 15000, 'https://th.bing.com/th/id/R104a3f14d72d76cf30f5208e66bcba01?rik=XRS2Nlp%2bm%2fVitQ&riu=http%3a%2f%2fmaisoncoffeemilktea.com%2fupload%2fsanpham%2fca-phe-sua-da-1271.jpg&ehk=LlV15cqcg8aNVhQ4hgHytyUiHJ5q%2bh6TejlxPcxcgOc%3d&risl=&pid=ImgRaw', 5),
(58, 'Cafe Đen Đá', 15000, 'https://sevencafe.net/wp-content/uploads/2018/10/cafedenda.jpg', 5),
(59, 'Sữa Chua Dâu Tằm', 20000, 'https://th.bing.com/th/id/R80acb35a5e210ae3f6eb542941b23e6c?rik=vnxJzm9C4wRubg&riu=http%3a%2f%2fwww.foodnfocus.com%2fwp-content%2fuploads%2f2015%2f01%2fIMG_5472-594x891.jpg&ehk=lpM02z1o%2fu8LKaEuznK1%2f50FCX4KfQMsNNUQrQh%2bT28%3d&risl=&pid=ImgRaw', 5),
(60, 'Sữa Chua Việt Quất', 23000, 'https://milan.com.vn/wp-content/uploads/2020/08/sinh-to-lam-dep-da-viet-quat-va-sua-chua-vani.jpg', 5),
(61, 'Trà Đào', 12000, 'https://th.bing.com/th/id/OIP.35gbLby-Z1sTfRnzM3woBAD6D6?pid=ImgDet&rs=1', 5),
(62, 'Trà Chanh', 13000, 'https://th.bing.com/th/id/R64afe17ba9a62ed6e2bdb98c38103286?rik=t3brrpHvOjkjsw&riu=http%3a%2f%2fsonghanhphuc.net%2fsites%2fdefault%2ffiles%2ftra_chanh.jpg&ehk=r8QOFn2r8qPQezBxzcGyBGVDa6a%2bG9RrdwxeNRWWLHM%3d&risl=&pid=ImgRaw', 5),
(63, 'Hồng Trà Vải', 25000, 'https://th.bing.com/th/id/R97038e0e634379665bb05014cf906bd4?rik=Wq7vtxjQVpvyJg&riu=http%3a%2f%2f4.bp.blogspot.com%2f-qZ7Cxo6-3yM%2fVW5SvOMTWOI%2fAAAAAAAAAQY%2fgoCeTJArBRk%2fs1600%2fsirup_od_malina_n_0.jpg&ehk=aXFoY8KK0R5inst3Va6qIgNlMO3V1D8aCAu14StB4N0%3d&risl=&pid=ImgRaw', 5),
(64, 'Trà Gừng', 11000, 'https://th.bing.com/th/id/OIP.EpKCb92O3KieNMq8p3SayAEsDH?pid=ImgDet&rs=1', 5),
(65, 'Sinh Tố Bơ', 23000, 'https://th.bing.com/th/id/OIP.nup49nm81X0VrWx0CE1rOgHaHW?pid=ImgDet&rs=1', 5),
(66, 'Sinh tố dưa hấu', 12000, 'https://bizweb.dktcdn.net/100/326/773/products/3.jpg?v=1534946223313', 5),
(67, 'Sinh Tố Dứa', 24000, 'https://agiadinh.net/wp-content/uploads/2017/04/sinh-to-dua-1-600x647.jpeg', 5),
(68, 'Sinh tố hỗn hợp kem, xoài, chuối', 15000, 'https://res.cloudinary.com/swiggy/image/upload/fl_lossy,f_auto,q_auto/gulkmk0iwl0okbj3lxjb', 5),
(69, 'Nước ép cà chua', 10000, 'https://trimunmurad.com/wp-content/uploads/2019/05/nuoc-ep-ca-chua.jpg', 5),
(70, 'Nước ép cà rốt', 16000, 'https://th.bing.com/th/id/OIP.L577hlJ9kD11DOdE4HNskAAAAA?pid=ImgDet&w=474&h=474&rs=1', 5),
(71, 'Nước cam ép', 15000, 'https://th.bing.com/th/id/R96ea600f7edf7ceb9556fd224f5b7de6?rik=BpAbisVoMfgCbQ&pid=ImgRaw', 5),
(72, 'Nước ép dứa', 12000, 'https://thuocdantoc.vn/wp-content/uploads/2018/12/nuoc-ep-dua-tri-ho-1.jpg', 5),
(73, 'Nước mía', 6000, 'https://cdn.phunusuckhoe.vn/tranvlt/auto/21_6_2019/ban-da-biet-cach-giai-ruou-bang-nuoc-mia3-2019-06-22-00-40.jpg', 5),
(74, 'Trà Sữa Truyền Thống', 23000, 'https://www.bartender.edu.vn/wp-content/uploads/2015/11/tra-sua-truyen-thong.jpg', 5),
(75, 'Trà Sữa Thạch Phô Mai', 25000, 'https://th.bing.com/th/id/R48c6a2e538fb1974132c35aa9b397bdf?rik=rbgtFRy%2bIvNo3A&riu=http%3a%2f%2failien.vn%2fdata%2fupload%2fTra-sua-thach-pho-mai-vua-beo-ngay-vua-da-con-khat-ngay-nang-nong-tra-sua2.jpg&ehk=95fCJIZJ7fXJ7%2f3fHLDT%2b1zrYwAlo8VXRcZCeSmLUVA%3d&risl=&pid=ImgRaw', 5),
(76, 'Chè Đậu Xanh', 12000, 'https://kitchen.sayidaty.net/uploads/small/df/dfd31405040c88dbd8a83f96016a3fcf_w750_h500.jpg', 4),
(77, 'Chè Đậu Đỏ', 12000, 'https://th.bing.com/th/id/Re4676fe97fdfcea395534074ea0d04f4?rik=uceP4DJhcrnE4Q&riu=http%3a%2f%2frecipe0.hoto.cn%2fpic%2frecipe%2fl%2fb6%2f11%2f463286_09029d.jpg&ehk=TPqEvOOaLsxoKAGrG4iCXRM2B5L23vD0YleQPA93m8Y%3d&risl=&pid=ImgRaw', 4),
(78, 'Chè Đậu Đen', 12000, 'https://th.bing.com/th/id/Ra81fd967c388c133473eedf733beca60?rik=ZezyM5NuuNhF0g&pid=ImgRaw', 4),
(79, 'Chè Thái', 15000, 'https://th.bing.com/th/id/R6348ef1b8fbadabfff23980581e6b561?rik=fp8cSXGXV0Eiew&pid=ImgRaw', 4),
(80, 'Chè Thập Cẩm', 15000, 'https://i0.wp.com/cacmonchengon.weebly.com/uploads/1/0/1/4/101486906/che-thap-cam_1_orig.jpg', 4),
(81, 'Chè Bưởi', 15000, 'https://www.huongnghiepaau.com/wp-content/uploads/2019/01/che-buoi-an-giang.jpg', 4),
(82, 'Sâm Bổ Lượng', 15000, 'https://th.bing.com/th/id/OIP.LXpPl263I59aSFJxz74F-QHaHa?pid=ImgDet&rs=1', 4),
(85, 'Bánh Flan', 10000, 'https://dl.airtable.com/KDGfLnihSDqdBpDljsqm_banh-flan-sua-tuoi-2-large%402x.jpg.jpg', 4),
(86, 'Sương Sâm, Sương Sáo', 15000, 'https://img-global.cpcdn.com/recipes/8a3922232080fbc7/751x532cq70/s%C6%B0%C6%A1ng-sam-s%C6%B0%C6%A1ng-sao-th%E1%BA%ADp-c%E1%BA%A9m-recipe-main-photo.jpg', 4),
(87, 'chè Hạt Sen', 15000, 'https://cdn.huongnghiepaau.com/wp-content/uploads/2017/08/che-hat-sen-nho-kho.jpg', 4),
(88, 'Gà Nướng Muối Ớt', 170000, 'https://th.bing.com/th/id/Re28edb4f569bd56c2acbd7540f3876c9?rik=cRxxYdLjPtqMkw&pid=ImgRaw', 6),
(89, 'Gà Nướng Sa Tế', 160000, 'https://i.pinimg.com/originals/e0/87/e3/e087e334e7358c2e7ec78c18fb5a081b.png', 6),
(90, 'Mực Nướng Sa Tế', 150000, 'https://th.bing.com/th/id/OIP.i6pB_dVddIF0JHi2l0xtQAHaHa?pid=ImgDet&rs=1', 6),
(91, 'Cá Nướng Giấy Bạc', 140000, 'https://th.bing.com/th/id/R0d8600adb5d0c6e9e807600b52dd011d?rik=D5i%2bmTk6YRM60g&pid=ImgRaw', 6),
(92, 'Sường Nướng BBQ', 100000, 'http://site-880172.mozfiles.com/files/880172/cac-mon-nuong-de-lam-tai-nha-10.jpg', 6),
(93, 'Lòng Heo Nướng', 50000, 'http://site-880172.mozfiles.com/files/880172/cac-mon-nuong-de-lam-tai-nha-9.jpg', 6),
(94, 'Nấm Nướng Chao', 40000, 'http://site-880172.mozfiles.com/files/880172/nam_nuong_5.png', 6),
(95, 'Cà Tím Nướng Mỡ Hành', 30000, 'http://site-880172.mozfiles.com/files/880172/cac-mon-nuong-de-lam-tai-nha-11.jpg', 6),
(96, 'Tốm Nướng Muối Ớt', 130000, 'https://travelgear.vn/blog/wp-content/uploads/2019/11/t%C3%B4m-n%C6%B0%E1%BB%9Bng-muoi.jpg', 6),
(97, 'Bò Cuốn Lá Lốt', 70000, 'https://www.huongnghiepaau.com/wp-content/uploads/2019/01/bo-cuon-la-lot.jpg', 6),
(98, 'Bạch Tuộc Nướng Sa Tế', 230000, 'https://travelgear.vn/blog/wp-content/uploads/2019/11/mon-nuong-binh-dan.jpg', 6),
(99, 'Cá Hồi Nướng Chanh', 350000, 'https://th.bing.com/th/id/OIP.Laiv0CMJhV05Ifauksm6rAHaEc?pid=ImgDet&rs=1', 6),
(100, 'Sò Huyết Nướng Sa Tế', 150000, 'https://travelgear.vn/blog/wp-content/uploads/2019/11/428_so_huyet_nuong.jpg', 6);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `nhomquyen`
--

CREATE TABLE `nhomquyen` (
  `ID` int(11) NOT NULL,
  `Permission` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `nhomquyen`
--

INSERT INTO `nhomquyen` (`ID`, `Permission`) VALUES
(1, 'Admin'),
(2, 'Users');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `taikhoang`
--

CREATE TABLE `taikhoang` (
  `SDT` varchar(50) NOT NULL,
  `Email` varchar(250) NOT NULL,
  `TenDangNhap` varchar(250) NOT NULL,
  `MatKhau` varchar(250) NOT NULL,
  `Permission` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Đang đổ dữ liệu cho bảng `taikhoang`
--

INSERT INTO `taikhoang` (`SDT`, `Email`, `TenDangNhap`, `MatKhau`, `Permission`) VALUES
('0123456789', '5951071106@st.utc2.edu.vn', 'Nguyen Huu Tin', 'tin', 2),
('0941866373', 'nguyenhuutin369@gmail.com', 'admin', 'admin', 1);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `ctdh`
--
ALTER TABLE `ctdh`
  ADD PRIMARY KEY (`MaDH`,`MaMonAn`),
  ADD KEY `MaMonAn` (`MaMonAn`);

--
-- Chỉ mục cho bảng `donhang`
--
ALTER TABLE `donhang`
  ADD PRIMARY KEY (`MaDH`),
  ADD KEY `SDT` (`SDT`);

--
-- Chỉ mục cho bảng `loaimonan`
--
ALTER TABLE `loaimonan`
  ADD PRIMARY KEY (`MaLoai`);

--
-- Chỉ mục cho bảng `monan`
--
ALTER TABLE `monan`
  ADD PRIMARY KEY (`MaMonAn`),
  ADD KEY `MaLoai` (`MaLoai`);

--
-- Chỉ mục cho bảng `nhomquyen`
--
ALTER TABLE `nhomquyen`
  ADD PRIMARY KEY (`ID`);

--
-- Chỉ mục cho bảng `taikhoang`
--
ALTER TABLE `taikhoang`
  ADD PRIMARY KEY (`SDT`),
  ADD UNIQUE KEY `Email` (`Email`),
  ADD UNIQUE KEY `SDT` (`SDT`),
  ADD KEY `Permission` (`Permission`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `donhang`
--
ALTER TABLE `donhang`
  MODIFY `MaDH` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT cho bảng `loaimonan`
--
ALTER TABLE `loaimonan`
  MODIFY `MaLoai` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT cho bảng `monan`
--
ALTER TABLE `monan`
  MODIFY `MaMonAn` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=101;

--
-- AUTO_INCREMENT cho bảng `nhomquyen`
--
ALTER TABLE `nhomquyen`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `ctdh`
--
ALTER TABLE `ctdh`
  ADD CONSTRAINT `ctdh_ibfk_1` FOREIGN KEY (`MaDH`) REFERENCES `donhang` (`MaDH`),
  ADD CONSTRAINT `ctdh_ibfk_2` FOREIGN KEY (`MaMonAn`) REFERENCES `monan` (`MaMonAn`);

--
-- Các ràng buộc cho bảng `donhang`
--
ALTER TABLE `donhang`
  ADD CONSTRAINT `donhang_ibfk_1` FOREIGN KEY (`SDT`) REFERENCES `taikhoang` (`SDT`);

--
-- Các ràng buộc cho bảng `monan`
--
ALTER TABLE `monan`
  ADD CONSTRAINT `monan_ibfk_1` FOREIGN KEY (`MaLoai`) REFERENCES `loaimonan` (`MaLoai`);

--
-- Các ràng buộc cho bảng `taikhoang`
--
ALTER TABLE `taikhoang`
  ADD CONSTRAINT `taikhoang_ibfk_1` FOREIGN KEY (`Permission`) REFERENCES `nhomquyen` (`ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
