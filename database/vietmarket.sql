-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 08, 2018 at 07:07 AM
-- Server version: 10.1.24-MariaDB
-- PHP Version: 7.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `vietmarket`
--

-- --------------------------------------------------------

--
-- Table structure for table `banner`
--

CREATE TABLE `banner` (
  `id` int(11) NOT NULL,
  `title` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `image` text COLLATE utf8_unicode_ci NOT NULL,
  `type` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `banner`
--

INSERT INTO `banner` (`id`, `title`, `image`, `type`) VALUES
(1, 'Thế giới công nghệ', '/img/banner/bn1.jpg', 1),
(2, 'Lễ hội mua sắm', '/img/banner/bn2.jpg', 1),
(3, 'Sản phẩm mới chào hè', '/img/banner/bn3.jpg', 1),
(4, 'Tinh hoa công nghệ', '/img/banner/bn4.jpg', 1);

-- --------------------------------------------------------

--
-- Table structure for table `brand`
--

CREATE TABLE `brand` (
  `id_brand` int(11) NOT NULL,
  `name_brand` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `img_brand` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `brand`
--

INSERT INTO `brand` (`id_brand`, `name_brand`, `img_brand`) VALUES
(1, 'Apple', '/img/brand/apple.png'),
(2, 'Xiaomi', '/img/brand/xiaomi.png'),
(3, 'LG', '/img/brand/lg.png'),
(4, 'Canon', '/img/brand/canon.png'),
(5, 'Hp', '/img/brand/hp.png'),
(6, 'Dell', '/img/brand/dell.png'),
(7, 'Samsung', '/img/brand/samsung1.png'),
(8, 'Nikon', '/img/brand/nikon.jpg'),
(9, 'Acer', '/img/brand/acer.png'),
(10, 'Asus', '/img/brand/asus.png'),
(11, 'Lenovo', '/img/brand/lenovo.png'),
(12, 'Blackberry', '/img/brand/blackberry.png'),
(13, 'Fuljifilm', '/img/brand/fuljifilm.png'),
(14, 'Sony', '/img/brand/sony.png'),
(15, 'Hitachi', '/img/brand/hitachi.png'),
(16, 'Mitsubishi', '/img/brand/mitsubishi.png'),
(17, 'Oppo', '/img/brand/oppo.png'),
(18, 'Panasonic', '/img/brand/panasonic.png'),
(19, 'Toshiba', '/img/brand/toshiba.png'),
(20, 'Alaska', '/img/brand/alaska.png'),
(21, 'Lock&lock', '/img/brand/lockandlock.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `comment`
--

CREATE TABLE `comment` (
  `id_comment` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `id_product` int(11) NOT NULL,
  `content` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `date` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `discount`
--

CREATE TABLE `discount` (
  `id` int(11) NOT NULL,
  `condition_discount` decimal(10,0) NOT NULL,
  `code` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `amount` decimal(10,0) NOT NULL,
  `start_date` bigint(20) NOT NULL,
  `end_date` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `notification`
--

CREATE TABLE `notification` (
  `id` int(11) NOT NULL,
  `type` int(11) NOT NULL,
  `title` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `content` varchar(500) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `order_details`
--

CREATE TABLE `order_details` (
  `id` int(11) NOT NULL,
  `id_order` int(11) NOT NULL,
  `id_product` int(11) NOT NULL,
  `id_shop` int(11) NOT NULL,
  `number` int(11) NOT NULL,
  `date_order_shop` bigint(20) NOT NULL,
  `status` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `order_details`
--

INSERT INTO `order_details` (`id`, `id_order`, `id_product`, `id_shop`, `number`, `date_order_shop`, `status`) VALUES
(1, 1, 1, 3, 1, 1527506366206, 2),
(2, 2, 4, 4, 1, 1528338978047, 0);

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `id` int(11) NOT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `image` text COLLATE utf8_unicode_ci NOT NULL,
  `price` decimal(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`id`, `name`, `image`, `price`) VALUES
(1, 'Thẻ tín dụng, ghi nợ', '/img/payment/ic_creditcard.png', '0'),
(2, 'Thanh toán khi nhận hàng', '/img/payment/ic_cod.png', '0');

-- --------------------------------------------------------

--
-- Table structure for table `popular_search`
--

CREATE TABLE `popular_search` (
  `id` int(11) NOT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `image` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `popular_search`
--

INSERT INTO `popular_search` (`id`, `name`, `image`) VALUES
(1, 'điện thoại', '/img/popularsearch/dienthoai.jpg'),
(2, 'laptop', '/img/popularsearch/laptop.jpg'),
(3, 'máy tính', '/img/popularsearch/maytinh.jpg'),
(4, 'cây máy tính', '/img/popularsearch/caymaytinh.jpg'),
(5, 'màn hình', '/img/popularsearch/maytinh.jpg'),
(6, 'máy ảnh', '/img/popularsearch/maytinh.jpg'),
(7, 'máy giặt', '/img/popularsearch/maytinh.jpg'),
(8, 'tivi', '/img/popularsearch/maytinh.jpg'),
(9, 'macbook', '/img/popularsearch/maytinh.jpg'),
(10, 'loa', '/img/popularsearch/maytinh.jpg');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id_product` int(11) NOT NULL,
  `name_product` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `price` decimal(10,0) NOT NULL,
  `image` text COLLATE utf8_unicode_ci NOT NULL,
  `information` text COLLATE utf8_unicode_ci NOT NULL,
  `weight` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `type_product` int(11) NOT NULL,
  `brand` int(11) NOT NULL,
  `rate` float NOT NULL,
  `amount` int(11) NOT NULL,
  `id_shop` int(11) NOT NULL,
  `hightlight` int(1) NOT NULL,
  `discount` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id_product`, `name_product`, `price`, `image`, `information`, `weight`, `type_product`, `brand`, `rate`, `amount`, `id_shop`, `hightlight`, `discount`) VALUES
(1, 'Laptop HP envy', '20000000', '/img/product/hp_envy.jpg@/img/product/hp_envy.jpg@/img/product/hp_envy.jpg@/img/product/hp_envy.jpg', 'Sản phẩm máy tính laptop văn phòng do hãng HP sản xuất', '2 Kg', 2, 5, 4.3, 9, 3, 1, 10),
(2, 'Latop HP pavilio', '15000000', '/img/product/hp_pavilion.jpg@/img/product/hp_pavilion.jpg@/img/product/hp_pavilion.jpg', 'Sản phẩm máy tính laptop văn phòng do hãng HP sản xuất', '1.5 Kg', 2, 5, 4.4, 10, 3, 0, 0),
(3, 'Laptop Asus ROG', '35000000', '/img/product/asus_rog.jpg@/img/product/asus_rog.jpg@/img/product/asus_rog.jpg@/img/product/asus_rog.jpg', 'Sản phẩm laptop gaming của hãng Asus', '3 Kg', 3, 10, 4.5, 7, 4, 1, 20),
(4, 'Điện thoại Asus Zenfone max', '5000000', '/img/product/asus_zenmax.jpg@/img/product/asus_zenmax.jpg@/img/product/asus_zenmax.jpg@/img/product/asus_zenmax.jpg', 'Điện thoại smartphone thế hệ thứ 5 do hãng Asus sản xuất', '200 gam', 1, 10, 4, 9, 4, 1, 0),
(5, 'Điện thoại iPhone X 256GB', '34790000', '/img/product/iphone1.jpg@/img/product/iphone2.jpg@/img/product/iphone3.jpg@/img/product/iphone4.jpg', 'iPhone X lột xác hoàn toàn với thiết kế mới độc đáo. Màn hình tràn viền phủ hầu hết mặt trước loại bỏ luôn nút home mang đến một trải nghiệm mới vô cùng độc đáo và khác biệt.', '200 gam', 1, 1, 0, 10, 6, 1, 5),
(6, 'Điện thoại Samsung Galaxy S9', '19990000', '/img/product/ss1.jpg@/img/product/ss2.jpg@/img/product/ss3.jpg@/img/product/ss4.jpg', 'Vẫn thừa hường một thiết kế đẹp từ người đàn anh đi trước là Samsung S8 với màn hình vô cực quyến rũ, nhưng Samsung Galaxy S9 đã chỉnh chu hơn phần nào nhờ cảm biến vân tay đặt dọc được người dùng đánh giá cao.', '250 gam', 1, 7, 4.4, 10, 7, 1, 10),
(7, 'Điện thoại Sony Xperia XZ2', '19990000', '/img/product/sony1.jpg@/img/product/sony2.jpg@/img/product/sony3.jpg@/img/product/sony4.jpg', 'Thay vì có thiết kế đậm chất nam tính như các đàn anh thì nay Sony Xperia XZ2 đã trở nên nữ tính hơn với mặt lưng được bo cong mềm mại để giúp tay của bạn ôm gọn được chiếc máy dễ dàng hơn. ', '200 gam', 1, 14, 4.5, 10, 7, 1, 20),
(8, 'Dell Inspiron 3462 N4200/4GB/500GB/Dos/(6PFTF11)\r\n', '7990000', '/img/product/dell1.jpg@/img/product/dell2.jpg@/img/product/dell3.jpg@/img/product/dell4.jpg', 'Máy tính xách tay Dell Inspiron 3462 N4200 là sản phẩm phổ thông, giá thành thấp, người dùng dễ tiếp cận và máy có cấu hình vừa đủ để xử lí các thao tác căn bản, công việc, học tập hằng ngày. ', '2 Kg', 2, 6, 0, 10, 7, 1, 0),
(9, 'Asus X407UA i3 6006U/4GB/1TB/Win10/(BV129T)', '10490000', '/img/product/asus1.jpg@/img/product/asus2.jpg@/img/product/asus3.jpg@/img/product/asus4.jpg', 'Asus X407UA là chiếc laptop nhỏ gọn, hiệu năng vừa phải phục vụ cho các nhu cầu phổ thông như lướt web, xem phim nghe nhạc, máy phù hợp với người dùng cần một chiếc máy laptop để phục vụ công việc, học tập.', '1.5 Kg', 2, 10, 4.4, 10, 7, 1, 20),
(10, 'Laptop Dell Inspiron N7567B /Gaming i7 VGA 1050Ti /Màn hình 4K', '29989000', '/img/product/dell_game1.jpg@/img/product/dell_game1.jpg', 'Dell Inspiron 7567 i7 7700HQ là dòng sản phẩm cao cấp của hãng, có thế mạnh nâng cao về cấu hình và hiệu suất hoạt động. Dell Inspiron 7567 i7 7700HQ có độ dày 2.544 cm, vỏ máy được làm từ chất liệu nhựa và nặng 2.62 kg đem lại cho bạn cảm giác khi cầm là rất \"đầm\" và chắc chắn khi sử dụng.', '2.62 Kg', 3, 6, 4.4, 10, 7, 1, 20),
(11, 'Apple Macbook Air MQD32SA/A i5 1.8GHz/8GB/128GB (2017)', '19990000', '/img/product/macair1.jpg@/img/product/macair2.jpg@/img/product/macair3.jpg@/img/product/macair4.jpg', 'Với thiết kế truyền thống của dòng MacBook Air, phiên bản 2017 này cũng không có thay đổi khi được trang bị lớp vỏ nhôm nguyên khối Unibody sang trọng, chỉ mỏng 17 mm và trọng lượng là 1.35 kg, rất tiện lợi và dễ dàng để bạn luôn mang theo bên mình. Logo quả táo Apple phát sáng tạo nên đặc trưng riêng khác biệt.\r\n\r\n', '1.35 Kg', 4, 1, 4.5, 10, 6, 1, 15),
(12, 'Apple Macbook Pro MPXT2SA/A i5 2.3GHz/8GB/256GB (2017)', '38990000', '/img/product/macpro1.jpg@/img/product/macpro2.jpg@/img/product/macpro3.jpg@/img/product/macpro4.jpg', 'Macbook Pro MPXT2SA/A 2017 thuộc dòng sản phẩm cao cấp nhất hiện nay của Apple cả về chất lượng thiết kế, độ bền sản phẩm và cả cấu hình mạnh mẽ, màn hình tuyệt vời nhất. Vỏ ngoài nguyên khối kim loại, đường nét thiết kế tinh tế và hệ thống tản nhiệt hiệu quả nhưng vẫn đảm bảo độ mỏng, nhẹ tối ưu dễ di chuyển.', '2 Kg', 4, 1, 4, 10, 6, 1, 10),
(13, 'Apple Macbook 12\" MNYK2SA/A Core M 1.2GHz/8GB/256GB (2017)', '33990000', '/img/product/mac121.jpg@/img/product/mac122.jpg@/img/product/mac123.jpg@/img/product/mac124.jpg', 'Apple Macbook 12” MNYF2SA/A 2017 là mẫu laptop được nhiều người dùng lựa chọn nhất vì máy có thiết kế mỏng nhẹ, đẹp nên phù hợp với nhu cầu sử dụng của nhiều người và đặc biệt là lựa chọn ưa thích của nhân viên văn phòng.', '1.2 Kg', 4, 1, 4.5, 10, 6, 1, 5),
(14, 'Máy tính bộ Dell Vostro 3268ST G4560 (9C32X1)', '7390000', '/img/product/dellbo1.jpg@/img/product/dellbo2.jpg@/img/product/dellbo3.jpg@/img/dellbo4/mac124.jpg', 'Mẫu thiết kế hiện đại ít tốn diện tích , không gian làm việc của bạn mà vẫn đảm bảo về hiệu xuất máy bền bỉ bởi các thoát nhiệt và làm mát máy khi ở nhu cầu sữ dụng liên tục trong khoảng thời gian dài.', '5 Kg', 5, 6, 0, 10, 7, 1, 10),
(15, 'Máy tính bộ HP Pavilion 570 p011l G4560 (Z8H69AA)', '7850000', '/img/product/hpbo1.jpg@/img/product/hpbo2.jpg@/img/product/hpbo3.jpg@/img/product/hpbo4.jpg', 'Mặc dù laptop, tablet hay smartphone đang ngày càng chiếm vị thế trên thị trường hiện nay bởi tính năng nhỏ gọn và nhiều tiện ích hiện đại tuy nhiên không thể phủ nhận một điều là máy tính để bàn luôn có một chỗ đứng nhất định với cấu hình vượt trội hơn hẳn, bền bỉ với thời gian. Chính vì thế, máy tính để bàn vẫn được các doanh nghiệp, các gia đình lựa chọn để hỗ trợ tốt cho công việc của mình. Phúc Anh xin giới thiệu máy tính để bàn HP Pavilion 570-p011l Z8H69AA với thiết kế hiện đại cùng hiệu năng hoạt động mạnh mẽ vẫn luôn được các khách hàng có nhu cầu sử dụng tin cậy.', '6.5 Kg', 5, 5, 4.5, 10, 3, 1, 15),
(16, 'Máy tính bộ HP Pavilion 570 p011l G4560 (Z8H69AA)', '7850000', '/img/product/hpbo21.jpg@/img/product/hpbo22.jpg@/img/product/hpbo23.jpg@/img/dellbo4/hpbo24.jpg', 'HP Pavilion 570-p011l Z8H69AA nổi bật với vẻ ngoài tinh tế và mạnh mẽ, mang đến phong cách hiện đại cho không gian làm việc của bạn. Vị trí các khe tản nhiệt được bố trí hợp lý ở vỏ máy và nguồn giúp tản nhiệt hiệu quả. Các nút điều khiển cũng như các cổng kết nối cũng được sắp xếp trực quan, giúp người dùng dễ dàng theo dõi và điều chỉnh.', '7 Kg', 5, 5, 4, 10, 3, 1, 0),
(17, 'Màn hình Samsung LCD PLS LS19F350 HNEXXV 18.5 inch HD', '1990000', '/img/product/manhinhss1.jpg@/img/product/manhinhss2.jpg@/img/product/manhinhss3.jpg@/img/dellbo4/manhinhss4.jpg', 'Màn hình Samsung S19F350 - 18.5\" PLS Panel thiết kế tinh tế, kiểu dáng hiện đại với đường nét mỏng, mang đến cho bạn sự trải nghiệm tuyệt vời khi sử dụng.', '3 Kg', 7, 7, 4, 10, 7, 1, 1),
(18, 'Màn hình HP LCD 19KA T3U82AA 18.5 inch HD', '1750000', '/img/product/manhinhhp1.jpg@/img/product/manhinhhp2.jpg@/img/product/manhinhhp3.jpg@/img/product/manhinhhp4.jpg', 'Màn hình máy tính HP 19KA T3U82AA có thiết kế đơn giản, kích thước mỏng, nhẹ giúp tiết kiệm không gian. Gam màu đen sang trọng làm tôn lên nét sang trọng cho sản phẩm.', '1.5 Kg', 7, 5, 0, 10, 3, 1, 0),
(19, 'Màn hình Dell LCD E1916H 18.5 inch HD', '1990000', '/img/product/manhinhdell1.jpg@/img/product/manhinhdell1.jpg@/img/product/manhinhdell1.jpg@/img/product/manhinhdell1.jpg', 'Màn hình 18,5\" phù hợp cho giải trí lẫn công việc. \r\nĐộ phân giải HD 1366x768, hiển thị 16,7 triệu màu, thời gian đáp ứng 5ms đảm bảo đem lại trải nghiệm tuyệt vời khi sử dụng \r\nThiết kế mạnh mẽ, hiện đại. \r\nHình ảnh trung thực, sắc nét. ', '3 Kg', 7, 6, 4.3, 10, 7, 1, 10),
(20, 'Android Tivi Sony 4K 43 inch KD-43X7500E', '11190000', '/img/product/tvsony1.jpg@/img/product/tvsony2.jpg@/img/product/tvsony3.jpg@/img/product/tvsony4.jpg', 'Smart Tivi Sony 4K 43 inch KD-43X7500E sở hữu kiểu dáng tinh tế, thời trang cùng với kích thước màn hình rộng 43 inch, kết hợp khung viền chắc chắn, sắc sảo và tông màu đen quý phái.', '3 Kg', 8, 14, 4.5, 10, 7, 1, 15),
(21, 'Smart Tivi LG 43 inch 43UJ652T', '11700000', '/img/product/tvlg1.jpg@/img/product/tvlg2.jpg@/img/product/tvlg3.jpg@/img/product/tvlg4.jpg', 'Smart Tivi LG 43 inch 43UJ652T với thiết kế màn hình siêu mỏng, liền mạch giúp mở rộng tối đa không gian hiển thị.', '2.5 Kg', 8, 3, 4.5, 10, 7, 1, 0),
(22, 'Smart Tivi OLED LG 55EG9A7T(FHD)', '25890000', '/img/product/tvlg21.jpg@/img/product/tvlg22.jpg@/img/product/tvlg23.jpg@/img/product/tvlg24.jpg', 'Smart Tivi OLED LG 55EG9A7T(FHD) là chiếc smart tivi nằm trong bộ sưu tập những chiếc tivi Oled vừa được LG ra mắt trong năm 2017. Chiếc tivi đặc trưng với thiết kế siêu mỏng cùng vẻ ngoài hết sức sang trọng, tinh tế dễ dàng làm nổi bật không gian nhà bạn.', '3 Kg', 8, 3, 4.5, 10, 7, 1, 10),
(23, 'Smart Tivi Samsung 49 inch UA49M5523', '13900000', '/img/product/tvss1.jpg@/img/product/tvss2.jpg@/img/product/tvss3.jpg@/img/product/tvss4.jpg', 'Smart Tivi Samsung 49 inch UA49M5523 sở hữu thiết kế viền màn hình siêu mỏng cùng chân đế màu bạc hiện đại chắc chắn sẽ là điểm nhấn nổi bật cho thiết kế nội thất căn nhà bạn. Kích thước màn hình 49 inch vừa phải giúp bạn dễ dàng bố trí tivi ở nhiều không gian khác nhau.', '3 Kg', 8, 7, 4.4, 10, 7, 1, 25),
(24, 'Loa thanh 2.2 Samsung HW-J250/XV', '1490000', '/img/product/loass1.jpg@/img/product/loass2.jpg@/img/product/loass3.jpg@/img/product/loass4.jpg', 'Dàn âm thanh Sound Bar Samsung HW-J250/XV 2.2 thiết kế dạng thanh, kích thước nhỏ gọn giúp bạn tiết kiệm được diện tích đặt loa. Màu đen cho loa dễ dàng hài hòa với không gian.', '500 gam', 11, 7, 4.3, 10, 7, 1, 0),
(25, 'Loa không dây Samsung 360 WAM1500', '2240000', '/img/product/loasony1.jpg@/img/product/loasony2.jpg@/img/product/loasony3.jpg@/img/product/loasony14.jpg', 'Loa không dây Samsung 360 WAM1500 với thiết kế gọn nhẹ, đẹp mắt, chất lượng âm thanh tốt cùng khả năng kết nối không dây tiện ích, sẽ là lựa chọn thú vị dành cho tín đồ âm nhạc.', '2 Kg', 11, 7, 0, 10, 7, 1, 25),
(26, 'Tủ lạnh LG 601 lít GR-P247JS', '44790000', '/img/product/tulanhlg1.jpg@/img/product/tulanhlg2.jpg@/img/product/tulanhlg3.jpg@/img/product/tulanhlg4.jpg', 'Tủ lạnh LG GR-P247JS có thiết kế với vỏ ngoài sang trọng đẳng cấp. Dung tích tủ lạnh trên 601 lít giúp người dùng có thể lưu trữ số lượng lớn thực phẩm, phù hợp với những gia đình có đông thành viên (trên 7 người).', '10 Kg', 35, 3, 0, 10, 8, 1, 10),
(27, 'Tủ lạnh Hitachi inverter 605 lít R-S700PGV2 GBK', '38490000', '/img/product/tulanhhitachi1.jpg@/img/product/tulanhhitachi2.jpg@/img/product/tulanhhitachi3.jpg@/img/product/tulanhhitachi4.jpg', 'Tủ lạnh Hitachi inverter 605 lít R-S700PGV2 GBK có thiết kế khá tinh tế, hiện đại. Đây là chiếc tủ lạnh có dung tích lên đến 605 lít vì vậy khá phù hợp với những gia đình có đông thành viên từ 7 người trở lên.', '12 Kg', 35, 15, 4.5, 10, 8, 1, 5),
(28, 'Tủ lạnh Hitachi Inverter 589 lít R-S700GPGV2', '51490000', '/img/product/tulanhhitachi21.jpg@/img/product/tulanhhitachi22.jpg@/img/product/tulanhhitachi23.jpg@/img/product/tulanhhitachi24.jpg', 'Tủ lạnh Hitachi R-S700GPGV2 589 lít là chiếc điều hòa có thiết kế khá sang trọng và tinh tế, với kiểu tủ lớn, side by side 2 cửa, chiếc tủ lạnh này sẽ làm cho không gian của bạn thêm phần hiện đại. Tủ lạnh Hitachi có dung tích lên đến 589 lít, chính vì vậy nó rất phù hợp với những gia đình có đông thành viên, từ 7 người trở lên.', '10 Kg', 35, 15, 4.3, 10, 8, 1, 10),
(29, 'Tủ lạnh Samsung Inverter 208 lít RT20HAR8DDX/SV', '6290000', '/img/product/tulanhss1.jpg@/img/product/tulanhss2.jpg@/img/product/tulanhss3.jpg@/img/product/tulanhss4.jpg', 'Tủ lạnh Samsung Inverter 208 lít RT20HAR8DDX/SV có thiết kế dạng hai cửa, ngăn đá trên truyền thống đơn giản, quen thuộc với người tiêu dùng Việt, nổi bật với tông màu nâu thời thượng đi cùng mặt gương sang trọng. Thiết kế này tạo nên sự khác biệt cho sản phẩm và hứa hẹn sẽ chinh phục những khách hàng khó tính nhất.', '8 Kg', 35, 7, 4.4, 10, 8, 1, 0),
(30, 'Tủ đông Electrolux 92 lít Hitachi', '2890000', '/img/product/tudonghitachi1.jpg@/img/product/tudonghitachi2.jpg@/img/product/tudonghitachi3.jpg@/img/product/tudonghitachi4.jpg', 'Tủ đông Electrolux 92 lít Hitachi có thiết kế đơn giản nhưng không kém phần hiện đại, phù hợp để đặt ở mọi vị trí không gian. Với kiểu dáng nhỏ gọn, chiếc tủ đông mini 92 lít này sẽ là sự lựa chọn lý tưởng cho những gia đình có từ 1-3 thành viên.', '7 Kg', 36, 15, 0, 10, 8, 1, 10),
(31, 'Máy giặt Panasonic 7.6 kg NA-F76VG9HRV', '4590000', '/img/product/maygiatpana1.jpg@/img/product/maygiatpana2.jpg@/img/product/maygiatpana3.jpg@/img/product/maygiatpana4.jpg', 'Máy giặt Panasonic NA-F76VG9HRV có thiết kế tinh tế mang vẻ sang trọng và hiện đại, có khả năng đem lại sự độc đáo riêng cho nhà bạn. Khối lượng giặt được 7.6 kg của chiếc máy giặt Panasonic này phù hợp với các gia đình có từ 4 đến 5 thành viên.', '7.6 Kg', 16, 18, 4.4, 10, 8, 1, 5),
(32, 'Máy giặt Panasonic 13.5 kg NA-F135V5SRV', '13390000', '/img/product/maygiatpana21.jpg@/img/product/maygiatpana22.jpg@/img/product/maygiatpana23.jpg@/img/product/maygiatpana24.jpg', 'Máy giặt Panasonic NA-F135V5SRV nổi bật với kiểu dáng sang trọng nhưng không kém phần tinh tế. Máy có khối lượng giặt lên đến 13.5 kg, phù hợp với những gia đình đông người (trên 6 người).', '13.5 Kg', 16, 18, 4.3, 10, 8, 1, 10),
(33, 'Máy giặt LG Inverter 9 kg FC1409S2W', '12990000', '/img/product/maygiatlg1.jpg@/img/product/maygiatlg2.jpg@/img/product/maygiatlg3.jpg@/img/product/maygiatlg4.jpg', 'Với thiết kế cửa trước hiện đại, kết hợp cùng gam màu trắng đơn giản, trung tính, máy giặt LG FC1409S2W sẽ dễ dàng kết hợp với mọi kiểu không gian nội thất của gia đình bạn.', '9 Kg', 16, 3, 4.4, 10, 8, 1, 0),
(34, 'Máy giặt Toshiba 8.2 kg AW-F920LV WB', '5190000', '/img/product/maygiattsb1.jpg@/img/product/maygiattsb2.jpg@/img/product/maygiattsb3.jpg@/img/product/maygiattsb4.jpg', 'Máy giặt Toshiba AW-F920LV WB được thiết kế với vẻ ngoài độc đáo cùng màu sắc cũng khá là tươi mới, mang lại sự sinh động hiện đại hơn cho căn nhà của bạn. Khối lượng giặt 8.2 kg của chiếc máy giặt Toshiba này giúp cho nó trở nên khá phù hợp cho các gia đình có từ 4 đến 5 thành viên.', '8.2 Kg', 16, 19, 4, 10, 8, 1, 10),
(35, 'Máy sấy quần áo Sunhouse SHD2610', '910000', '/img/product/maygiattsb1.jpg@/img/product/maygiattsb2.jpg@/img/product/maygiattsb3.jpg@/img/product/maygiattsb4.jpg', 'Máy sấy quần áo Sunhouse SHD2610 với thiết kế lồng sấy hình trụ đứng, nhỏ, nhẹ dễ dàng tháo lắp, thuận tiện di chuyển hoặc cất giữ.', '2 Kg', 17, 20, 4.5, 10, 8, 1, 0),
(36, 'Điều hòa Panasonic 9040 BTU CU/CS-N9UKH-8', '8590000', '/img/product/dieuhoapana1.jpg@/img/product/dieuhoapana2.jpg@/img/product/dieuhoapana3.jpg@/img/product/dieuhoapana4.jpg', 'Ngôi nhà của gia chủ sẽ được điểm xuyến bởi điều hòa Panasonic 9040 BTU CU/CS-N9UKH-8 với thiết kế truyền thống cứng cáp, màu trắng tinh khôi tạo nên vẻ sang trọng không kém phần hiện đại. Công suất vận hành 9040 BTU phù hợp cho những không gian phòng ngủ hoặc phòng khách có diện tích 15 m2 đổ lại.', '4 Kg', 18, 18, 4, 10, 8, 1, 0),
(37, 'Điều hòa Samsung Inverter 9000 BTU AR10NVFHGWKNSV', '7990000', '/img/product/dieuhoass1.jpg@/img/product/dieuhoass2.jpg@/img/product/dieuhoass3.jpg@/img/product/dieuhoass4.jpg', 'Điều hòa Samsung Inverter 9000 BTU AR10NVFHGWKNSV được Samsung cho ra mắt trong năm 2018, với kiểu dáng hiện đại đi cùng tông màu trắng sang trọng sẽ phù hợp với nội thất xung quanh nhà bạn.Với công suất 9000 BTU sẽ phù hợp với những diện tích phòng nhỏ dưới 15 m2 như phòng ngủ, phòng làm việc nhỏ,...', '3.5 Kg', 18, 7, 0, 10, 8, 1, 0),
(38, 'Điều hòa Toshiba Inverter 8500 BTU RAS-H10DKCVG-V', '8990000', '/img/product/dieuhoatsb1.jpg@/img/product/dieuhoatsb2.jpg@/img/product/dieuhoatsb3.jpg@/img/product/dieuhoatsb4.jpg', 'Thương hiệu điều hòa gần gũi với người tiêu dùng Việt, Tosiba thiết kế dòng điều hòa Toshiba Inverter 8500 BTU RAS-H10DKCVG-V với kiểu dáng nhỏ gọn, công suất 8500 BTU phù hợp cho những không gian phòng ngủ hoặc phòng khách có diện tích 15 m2 đổ lại.', '3 Kg', 18, 19, 4.4, 10, 8, 1, 20),
(39, 'Bình nóng lạnh Alaska STAR B 15 R 2.5 FE', '2590000', '/img/product/binhnonglanhalaska.jpg@/img/product/binhnonglanhalaska.jpg@/img/product/binhnonglanhalaska.jpg@/img/product/binhnonglanhalaska.jpg', 'Chế độ an toàn ELCB đảm bảo an toàn  tuyện đối cho gia đình bạn\r\nBình chứa tráng men Titan, chống ăn mòn và tăng độ bền cho máy\r\nCông nghệ duy trì nhiệt độFlexomix, và bộ ổn định nhiệt TBST.', '2 Kg', 19, 20, 4.3, 10, 8, 1, 10),
(40, 'Bình nóng lạnh Mitsubishi SL 30 ST 2.5 FE - MT 30 lít', '3890000', '/img/product/binhnuocnongmsbs.jpg@/img/product/binhnuocnongmsbs.jpg@/img/product/binhnuocnongmsbs.jpg@/img/product/binhnuocnongmsbs.jpg', 'Tiết kiệm thời gian và chi phí hơn với khả năng làm nóng gián tiếp.Ngăn chặn nguy cơ bỏng rát cho da với hệ thống chống bỏng TSS hiện đại. Nước nóng đầu ra luôn sạch khuẩn và tinh khiết với hệ thống tạo Ion bạc kháng khuẩn.', '3 Kg', 19, 16, 0, 10, 8, 1, 0),
(41, 'Bình thủy điện Panasonic NC-EG4000CSY 4 lít', '1990000', '/img/product/binhthuydienpana1.jpg@/img/product/binhthuydienpana2.jpg@/img/product/binhthuydienpana3.jpg@/img/product/binhthuydienpana4.jpg', 'Bình thủy điện Panasonic NC-EG4000CSY thiết kế hiện đại, kiểu dáng nhỏ gọn. Bình có quai xách tay giúp người dùng sử dụng, di chuyển sản phẩm đơn giản.', '2 Kg', 20, 18, 4, 10, 8, 1, 10),
(42, 'Bàn là hơi nước Panasonic NI-P250TGRA', '1990000', '/img/product/banlapana1.jpg@/img/product/banlapana2.jpg@/img/product/banlapana3.jpg@/img/product/banlapana4.jpg', 'Bàn là hơi nước Panasonic NI-P250TGRA được thiết kế với kiểu dáng nhỏ gọn, hiện đại với gam màu xanh lá tươi mát. Các nút chức năng phân bố hợp lý, dễ sử dụng.', '1.5 Kg', 21, 18, 4.5, 10, 8, 1, 0),
(43, 'ghjjk', '12000', '/img/product/380_1528426283607.jpeg', 'ffgjjk', '12 gam', 6, 7, 0, 12, 9, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `product_details`
--

CREATE TABLE `product_details` (
  `id` int(11) NOT NULL,
  `id_product` int(11) NOT NULL,
  `ram` int(11) NOT NULL,
  `rom` int(11) NOT NULL,
  `resolution` int(11) NOT NULL,
  `size_screen` int(11) NOT NULL,
  `camera` int(11) DEFAULT NULL,
  `chipset` int(11) NOT NULL,
  `card` int(11) NOT NULL,
  `pin` int(11) NOT NULL,
  `OS` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `product_like`
--

CREATE TABLE `product_like` (
  `id_customer` int(11) NOT NULL,
  `id_product` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `product_like`
--

INSERT INTO `product_like` (`id_customer`, `id_product`) VALUES
(2, 2),
(2, 3),
(2, 4),
(5, 3);

-- --------------------------------------------------------

--
-- Table structure for table `product_order`
--

CREATE TABLE `product_order` (
  `id_order` int(11) NOT NULL,
  `id_user` int(11) NOT NULL,
  `fullname` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `phone` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `date_order` bigint(20) NOT NULL,
  `status` int(11) NOT NULL,
  `type_transport` int(11) NOT NULL,
  `type_payment` int(11) NOT NULL,
  `value` decimal(10,0) NOT NULL,
  `address` varchar(200) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `product_order`
--

INSERT INTO `product_order` (`id_order`, `id_user`, `fullname`, `phone`, `date_order`, `status`, `type_transport`, `type_payment`, `value`, `address`) VALUES
(1, 2, 'truong', '098776', 1527506366206, 0, 1, 1, '15020000', 'Thinh Liet'),
(2, 2, 'truong', '098776', 1528338978047, 1, 2, 2, '4995000', 'hello world');

-- --------------------------------------------------------

--
-- Table structure for table `promotion`
--

CREATE TABLE `promotion` (
  `id` int(11) NOT NULL,
  `code` varchar(6) COLLATE utf8_unicode_ci NOT NULL,
  `start` bigint(20) NOT NULL,
  `end` bigint(20) NOT NULL,
  `number` int(11) NOT NULL,
  `amount` decimal(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `promotion`
--

INSERT INTO `promotion` (`id`, `code`, `start`, `end`, `number`, `amount`) VALUES
(5, 'ItnNsH', 1525272330208, 1527691535076, 5, '10000'),
(6, 'a', 1525189672324, 1530373677760, 7, '20000');

-- --------------------------------------------------------

--
-- Table structure for table `rate`
--

CREATE TABLE `rate` (
  `id_product` int(11) NOT NULL,
  `user_rate` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `title` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `content` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `star` float NOT NULL,
  `date_rate` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `rate`
--

INSERT INTO `rate` (`id_product`, `user_rate`, `title`, `content`, `star`, `date_rate`) VALUES
(1, 'truong@gmail.com', 'acv', 'acv', 2, '24/03/2018'),
(1, 'truong1@gmail.com', 'dasd', 'dasda', 4, '24/03/2018'),
(2, 'truong@gmail.com', 'dasdad', 'dasdad', 4, '24/03/2018'),
(3, 'truong@gmail.com', 'San pham tot', 'okeeeee', 4.5, '24/03/2018'),
(4, 'truong@gmail.com', 'hello', 'dasdads', 4, '03/05/2018');

-- --------------------------------------------------------

--
-- Table structure for table `shop`
--

CREATE TABLE `shop` (
  `id_shop` int(11) NOT NULL,
  `name_shop` varchar(50) CHARACTER SET utf8 COLLATE utf8_unicode_520_ci NOT NULL,
  `slogan` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `img_avatar` text COLLATE utf8_unicode_ci NOT NULL,
  `img_cover` text COLLATE utf8_unicode_ci NOT NULL,
  `id_owner` int(11) NOT NULL,
  `address` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `phone` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `website` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `rate` float NOT NULL,
  `highlight` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `shop`
--

INSERT INTO `shop` (`id_shop`, `name_shop`, `slogan`, `img_avatar`, `img_cover`, `id_owner`, `address`, `phone`, `website`, `rate`, `highlight`) VALUES
(3, 'Hp Shop', 'Make it matter', '/img/shop/hp.png', '/img/shop/hp_cover.jpg', 2, '74 Cửa Bắc, Ba Đình, TP. Hà Nội', '02462627095', 'https://support.hp.com.vn', 5, 1),
(4, 'Asus Shop', 'In search of incredible', '/img/shop/asus1.png', '/img/shop/asus_bg.jpg', 5, 'Số 2 Trường Chinh, Hà Nội', '12345', 'abc.com', 1.5, 1),
(5, 'Nikon shop', 'At the heart of the image', '/img/shop/nikon.jpg', '/img/shop/nikon_bg.jpg', 11, '12 Hoàng Cầu Mới  Q.Đống Đa', '6267317686', 'abc.com', 4.3, 1),
(6, 'Apple Store', 'Think Different', '/img/shop/apple.png', '/img/shop/apple_bg.png', 13, 'Phường Binh Hưng Hoà A, Quận Bình Tân, Hồ Chí Minh', '0986338744', 'https://apple.com.vn', 0, 1),
(7, 'Thế giới di động', 'Lắng nghe và chia sẻ', '/img/shop/tgdd.jpg', '/img/shop/tgdd_bg.jpg', 14, '249 NGUYỄN CÔNG TRỨ, SƠN TRÀ, ĐÀ NẴNG', '0912066221', 'https://thegioididong.com.vn', 0, 1),
(8, 'Bách Hóa Online', 'Niềm vui mua sắm', '/img/shop/shoponline.jpg', '/img/shop/shoponline_bg.png', 15, 'Xã Thanh Mai, Huyện Thanh Oai, Hà Nội', '6267317686', 'https://bachhoaonline.vn', 0, 1),
(9, 'Shop Nam', 'hello', '/img/shop/809_1528425011937.jpeg', '/img/shop/76_1528425020054.jpeg', 16, 'Hà Nội', '0123456789', 'abc.com', 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `shop_details`
--

CREATE TABLE `shop_details` (
  `id` int(11) NOT NULL,
  `id_shop` int(11) NOT NULL,
  `id_product` int(11) NOT NULL,
  `number` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `shop_follow`
--

CREATE TABLE `shop_follow` (
  `id_shop` int(11) NOT NULL,
  `id_user` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `shop_follow`
--

INSERT INTO `shop_follow` (`id_shop`, `id_user`) VALUES
(3, 2),
(4, 2),
(4, 5);

-- --------------------------------------------------------

--
-- Table structure for table `transport`
--

CREATE TABLE `transport` (
  `id` int(11) NOT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `note` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `price` decimal(10,0) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `transport`
--

INSERT INTO `transport` (`id`, `name`, `note`, `price`) VALUES
(1, 'Giao hàng nhanh', 'Từ 2-4 ngày', '20000'),
(2, 'Giao hàng tiết kiệm', 'Từ 3-5 ngày', '15000'),
(3, 'ViettelPost', 'Phạm vi toàn quốc', '20000');

-- --------------------------------------------------------

--
-- Table structure for table `type_child`
--

CREATE TABLE `type_child` (
  `id_type_child` int(11) NOT NULL,
  `name_type_child` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `id_type_parent` int(11) NOT NULL,
  `image_cate` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `type_child`
--

INSERT INTO `type_child` (`id_type_child`, `name_type_child`, `id_type_parent`, `image_cate`) VALUES
(1, 'Điện thoại', 1, '/img/category/child_dienthoai.png'),
(2, 'Laptop văn phòng', 1, '/img/category/child_laptop_vanphong.png'),
(3, 'Latop Gaming', 1, '/img/category/child_laptop_gaming.png'),
(4, 'Macbook', 1, '/img/category/child_macbook.png'),
(5, 'Máy tính bộ', 2, '/img/category/child_maytinhbo.png'),
(6, 'Cây máy tính', 2, '/img/category/child_cay_maytinh.png'),
(7, 'Màn hình máy tính', 2, '/img/category/child_manhinhmaytinh.png'),
(8, 'Tivi', 3, '/img/category/child_tivi.png'),
(11, 'Dàn âm thanh', 3, '/img/category/child_amthanh.png'),
(12, 'Máy ảnh kỹ thuật số', 4, '/img/category/child_mayanhkythuatso.png'),
(13, 'Máy ảnh cơ', 4, '/img/category/child_mayanhphim.png'),
(14, 'Máy ảnh du lịch', 4, '/img/category/child_mayanhdulich.png'),
(15, 'Máy quay', 4, '/img/category/child_mayquay.png'),
(16, 'Máy giặt', 5, '/img/category/child_maygiat.png'),
(17, 'Máy sấy quần áo', 5, '/img/category/child_maysay.png'),
(18, 'Điều hòa', 6, '/img/category/child_dieuhoa.png'),
(19, 'Bình nóng lạnh', 6, '/img/category/child_binhnonglanh.png'),
(20, 'Bình thủy điện', 7, '/img/category/child_binhthuydien.png'),
(21, 'Bàn là', 7, '/img/category/child_banla.png'),
(22, 'Máy sấy tóc', 7, '/img/category/child_maysaytoc.png'),
(23, 'Quạt điện', 7, '/img/category/child_quatdien.png'),
(24, 'Bếp gas', 8, '/img/category/child_bepgas.png'),
(25, 'Bếp từ', 8, '/img/category/child_beptu.jpg'),
(26, 'Bộ nồi', 8, '/img/category/child_bonoi.png'),
(27, 'Chảo chống dính', 8, '/img/category/child_chaochongdinh.png'),
(28, 'Lò vi sóng', 8, '/img/category/child_lovisong.png'),
(29, 'Hộp đựng thực phẩm', 8, '/img/category/child_hopdungthucpham.png'),
(30, 'Máy xay sinh tố', 8, '/img/category/child_mayxaysinhto.png'),
(31, 'Nồi cơm điện', 8, '/img/category/child_noicomdien.png'),
(32, 'Máy in', 9, '/img/category/child_mayin.png'),
(33, 'Điện thoại bàn', 9, '/img/category/child_dienthoaiban.png'),
(34, 'Router - thiết bị mạng', 9, '/img/category/child_router.png'),
(35, 'Tủ lạnh', 10, '/img/category/child_tulanh.jpg'),
(36, 'Tủ đông', 10, '/img/category/child_tudong.png'),
(37, 'Phụ kiện điện thoại', 11, '/img/category/child_phukiendienthoai.png'),
(38, 'Phụ kiện máy tính', 11, '/img/category/child_phukienmaytinh.png'),
(39, 'Phụ kiện máy ảnh', 11, '/img/category/child_phukienmayanh.png');

-- --------------------------------------------------------

--
-- Table structure for table `type_parent`
--

CREATE TABLE `type_parent` (
  `id_type_parent` int(11) NOT NULL,
  `name_type_parent` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `image` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `type_parent`
--

INSERT INTO `type_parent` (`id_type_parent`, `name_type_parent`, `image`) VALUES
(1, 'Điện thoại, Laptop, Tablet', '/img/category/cate_dienthoai.png'),
(2, 'Máy tính bộ, Màn hình', '/img/category/cate_maytinh.png'),
(3, 'Tivi, Âm thanh', '/img/category/cate_tivi.png'),
(4, 'Máy ảnh, Máy quay', '/img/category/cate_mayanh.png'),
(5, 'Máy giặt, Sấy quần áo', '/img/category/cate_maygiat.png'),
(6, 'Điều hòa, Bình nóng lạnh', '/img/category/cate_dieuhoa.png'),
(7, 'Điện gia dụng', '/img/category/cate_diengiadung.png'),
(8, 'Đồ dùng nhà bếp', '/img/category/cate_nhabep.png'),
(9, 'Thiết bị văn phòng, Máy in', '/img/category/cate_mayin.png'),
(10, 'Tủ lạnh, Tủ đông', '/img/category/cate_tulanh.png'),
(11, 'Phụ kiện', '/img/category/cate_phukien.png');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id_user` int(11) NOT NULL,
  `fullname` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `username` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `password` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `address` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `birthday` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `phone` varchar(20) COLLATE utf8_unicode_ci NOT NULL,
  `gender` int(1) NOT NULL,
  `img_avatar` text COLLATE utf8_unicode_ci NOT NULL,
  `id_type` int(11) NOT NULL,
  `type_login` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id_user`, `fullname`, `username`, `password`, `address`, `birthday`, `phone`, `gender`, `img_avatar`, `id_type`, `type_login`) VALUES
(2, 'Vũ Xuân Trường', 'truong@gmail.com', '123', 'Nhà N1 chung cư Đồng Tàu@Số 1 Hai Bà Trưng, Hà Nội', '', '098776', 0, '/img/user/f.png', 2, 0),
(5, 'Trịnh Quang Trường', 'truong1@gmail.com', '1', 'số nhà 24 ấp 5b, Xã Lộc Tấn, Huyện Lộc Ninh, Bình Phước', '123', '2312', 0, '/img/user/d.png', 2, 0),
(11, 'Nguyễn Thị A', 'a@gmail.com', '123', 'LÔ 56/97 PHẠM VĂN ĐỒNG, P. TỨ MINH, TP. HẢI DƯƠNG, HẢI DƯƠNG', '', '123', 0, '/img/user/a.png', 2, 0),
(12, 'admin', 'admin@gmail.com', '123', 'abc', '', '213123', 0, '', 1, 0),
(13, 'Nguyễn Kim Loan', 'loan@gmail.com', '123', '355 Thị trấn Gia Bình, Huyện Gia Bình, Bắc Ninh', '', '0986338744', 0, '/img/user/b.png', 2, 0),
(14, 'Định Thị Quế', 'que@gmail.com', '123', '12 Triệu Việt Vương, Phường 4, Thành phố Đà Lạt, Lâm Đồng', '', '0912066221', 0, '/img/user/c.png', 2, 0),
(15, 'Tống Thị Hằng', 'hang@gmail.com', '123', 'KHU PHỐ THỊ TRẤN LỤC NAM, LỤC GIANG BẮC GIANG', '', '0924042081', 0, '/img/user/e.png', 2, 0),
(16, 'Nguyễn Văn Nam', 'nam@gmail.com', '123', '', '', '0983815459', 0, '', 2, 0);

-- --------------------------------------------------------

--
-- Table structure for table `user_type`
--

CREATE TABLE `user_type` (
  `id_user_type` int(11) NOT NULL,
  `name_user_type` varchar(50) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `user_type`
--

INSERT INTO `user_type` (`id_user_type`, `name_user_type`) VALUES
(1, 'Admin'),
(2, 'Customer');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `banner`
--
ALTER TABLE `banner`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `brand`
--
ALTER TABLE `brand`
  ADD PRIMARY KEY (`id_brand`);

--
-- Indexes for table `comment`
--
ALTER TABLE `comment`
  ADD PRIMARY KEY (`id_comment`,`id_user`,`id_product`),
  ADD KEY `fk_product_cmt` (`id_product`),
  ADD KEY `fk_user_cmt` (`id_user`);

--
-- Indexes for table `discount`
--
ALTER TABLE `discount`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `notification`
--
ALTER TABLE `notification`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `order_details`
--
ALTER TABLE `order_details`
  ADD PRIMARY KEY (`id`,`id_order`,`id_product`),
  ADD KEY `fk_order_details` (`id_order`),
  ADD KEY `fk_order_product` (`id_product`),
  ADD KEY `fk_details_shop` (`id_shop`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `popular_search`
--
ALTER TABLE `popular_search`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id_product`),
  ADD KEY `fk_brand` (`brand`),
  ADD KEY `fk_type_product` (`type_product`),
  ADD KEY `fk_product_shop` (`id_shop`);

--
-- Indexes for table `product_details`
--
ALTER TABLE `product_details`
  ADD KEY `fk_product_details` (`id_product`);

--
-- Indexes for table `product_like`
--
ALTER TABLE `product_like`
  ADD PRIMARY KEY (`id_customer`,`id_product`),
  ADD KEY `fk_product_like` (`id_product`);

--
-- Indexes for table `product_order`
--
ALTER TABLE `product_order`
  ADD PRIMARY KEY (`id_order`),
  ADD KEY `fk_order_transport` (`type_transport`),
  ADD KEY `fk_order_payment` (`type_payment`);

--
-- Indexes for table `promotion`
--
ALTER TABLE `promotion`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `code` (`code`);

--
-- Indexes for table `rate`
--
ALTER TABLE `rate`
  ADD PRIMARY KEY (`id_product`,`user_rate`),
  ADD KEY `fk_user_rate` (`user_rate`);

--
-- Indexes for table `shop`
--
ALTER TABLE `shop`
  ADD PRIMARY KEY (`id_shop`),
  ADD UNIQUE KEY `id_owner` (`id_owner`);

--
-- Indexes for table `shop_details`
--
ALTER TABLE `shop_details`
  ADD PRIMARY KEY (`id`,`id_shop`,`id_product`),
  ADD KEY `fk_shop_details` (`id_shop`),
  ADD KEY `fk_shop_product` (`id_product`);

--
-- Indexes for table `shop_follow`
--
ALTER TABLE `shop_follow`
  ADD PRIMARY KEY (`id_shop`,`id_user`),
  ADD KEY `fk_customer_follow` (`id_user`);

--
-- Indexes for table `transport`
--
ALTER TABLE `transport`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `type_child`
--
ALTER TABLE `type_child`
  ADD PRIMARY KEY (`id_type_child`),
  ADD KEY `fk_type_child` (`id_type_parent`);

--
-- Indexes for table `type_parent`
--
ALTER TABLE `type_parent`
  ADD PRIMARY KEY (`id_type_parent`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id_user`),
  ADD UNIQUE KEY `username` (`username`),
  ADD KEY `fk_type_user` (`id_type`);

--
-- Indexes for table `user_type`
--
ALTER TABLE `user_type`
  ADD PRIMARY KEY (`id_user_type`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `banner`
--
ALTER TABLE `banner`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT for table `brand`
--
ALTER TABLE `brand`
  MODIFY `id_brand` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;
--
-- AUTO_INCREMENT for table `comment`
--
ALTER TABLE `comment`
  MODIFY `id_comment` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `discount`
--
ALTER TABLE `discount`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `notification`
--
ALTER TABLE `notification`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `order_details`
--
ALTER TABLE `order_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `popular_search`
--
ALTER TABLE `popular_search`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id_product` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=44;
--
-- AUTO_INCREMENT for table `product_order`
--
ALTER TABLE `product_order`
  MODIFY `id_order` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `promotion`
--
ALTER TABLE `promotion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
--
-- AUTO_INCREMENT for table `shop`
--
ALTER TABLE `shop`
  MODIFY `id_shop` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;
--
-- AUTO_INCREMENT for table `shop_details`
--
ALTER TABLE `shop_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `transport`
--
ALTER TABLE `transport`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `type_child`
--
ALTER TABLE `type_child`
  MODIFY `id_type_child` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;
--
-- AUTO_INCREMENT for table `type_parent`
--
ALTER TABLE `type_parent`
  MODIFY `id_type_parent` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `comment`
--
ALTER TABLE `comment`
  ADD CONSTRAINT `fk_product_cmt` FOREIGN KEY (`id_product`) REFERENCES `product` (`id_product`),
  ADD CONSTRAINT `fk_user_cmt` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`);

--
-- Constraints for table `order_details`
--
ALTER TABLE `order_details`
  ADD CONSTRAINT `fk_details_shop` FOREIGN KEY (`id_shop`) REFERENCES `shop` (`id_shop`),
  ADD CONSTRAINT `fk_order_details` FOREIGN KEY (`id_order`) REFERENCES `product_order` (`id_order`),
  ADD CONSTRAINT `fk_order_product` FOREIGN KEY (`id_product`) REFERENCES `product` (`id_product`);

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `fk_brand` FOREIGN KEY (`brand`) REFERENCES `brand` (`id_brand`),
  ADD CONSTRAINT `fk_product_shop` FOREIGN KEY (`id_shop`) REFERENCES `shop` (`id_shop`),
  ADD CONSTRAINT `fk_type_product` FOREIGN KEY (`type_product`) REFERENCES `type_child` (`id_type_child`);

--
-- Constraints for table `product_details`
--
ALTER TABLE `product_details`
  ADD CONSTRAINT `fk_product_details` FOREIGN KEY (`id_product`) REFERENCES `product` (`id_product`);

--
-- Constraints for table `product_like`
--
ALTER TABLE `product_like`
  ADD CONSTRAINT `fk_customer_like` FOREIGN KEY (`id_customer`) REFERENCES `user` (`id_user`),
  ADD CONSTRAINT `fk_product_like` FOREIGN KEY (`id_product`) REFERENCES `product` (`id_product`);

--
-- Constraints for table `product_order`
--
ALTER TABLE `product_order`
  ADD CONSTRAINT `fk_order_payment` FOREIGN KEY (`type_payment`) REFERENCES `payment` (`id`),
  ADD CONSTRAINT `fk_order_transport` FOREIGN KEY (`type_transport`) REFERENCES `transport` (`id`);

--
-- Constraints for table `rate`
--
ALTER TABLE `rate`
  ADD CONSTRAINT `fk_product_rate` FOREIGN KEY (`id_product`) REFERENCES `product` (`id_product`),
  ADD CONSTRAINT `fk_user_rate` FOREIGN KEY (`user_rate`) REFERENCES `user` (`username`);

--
-- Constraints for table `shop`
--
ALTER TABLE `shop`
  ADD CONSTRAINT `fk_shop_owner` FOREIGN KEY (`id_owner`) REFERENCES `user` (`id_user`);

--
-- Constraints for table `shop_details`
--
ALTER TABLE `shop_details`
  ADD CONSTRAINT `fk_shop_details` FOREIGN KEY (`id_shop`) REFERENCES `shop` (`id_shop`),
  ADD CONSTRAINT `fk_shop_product` FOREIGN KEY (`id_product`) REFERENCES `product` (`id_product`);

--
-- Constraints for table `shop_follow`
--
ALTER TABLE `shop_follow`
  ADD CONSTRAINT `fk_customer_follow` FOREIGN KEY (`id_user`) REFERENCES `user` (`id_user`),
  ADD CONSTRAINT `fk_shop_follow` FOREIGN KEY (`id_shop`) REFERENCES `shop` (`id_shop`);

--
-- Constraints for table `type_child`
--
ALTER TABLE `type_child`
  ADD CONSTRAINT `fk_type_child` FOREIGN KEY (`id_type_parent`) REFERENCES `type_parent` (`id_type_parent`);

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `fk_type_user` FOREIGN KEY (`id_type`) REFERENCES `user_type` (`id_user_type`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
