-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 08, 2018 at 03:24 AM
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
  `image` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

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
(2, 'Samsung', '/img/brand/samsung.png'),
(3, 'Sony', '/img/brand/sony.png'),
(4, 'Xiaomi', '/img/brand/xiaomi.png'),
(5, 'Hp', '/img/brand/hp.png'),
(6, 'Dell', '/img/brand/dell.png'),
(7, 'Canon', '/img/brand/canon.png'),
(8, 'Nikon', '/img/brand/nikon.jpg'),
(9, 'Acer', '/img/brand/acer.png'),
(10, 'Asus', '/img/brand/asus.png'),
(11, 'Lenovo', '/img/brand/lenovo.png'),
(12, 'Blackberry', '/img/brand/blackberry.png'),
(13, 'Fuljifilm', '/img/brand/fuljifilm.png'),
(14, 'LG', '/img/brand/lg.png'),
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
  `number` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

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
(1, 'Thẻ tín dụng, ghi nợ', '/img/payment/ic_creditcard.png', '20000'),
(2, 'Thanh toán khi nhận hàng', '/img/payment/ic_cod.png', '20000');

-- --------------------------------------------------------

--
-- Table structure for table `popular_search`
--

CREATE TABLE `popular_search` (
  `id` int(11) NOT NULL,
  `name` varchar(50) COLLATE utf8_unicode_ci NOT NULL,
  `image` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

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
  `hightlight` int(1) NOT NULL,
  `discount` int(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id_product`, `name_product`, `price`, `image`, `information`, `weight`, `type_product`, `brand`, `rate`, `amount`, `hightlight`, `discount`) VALUES
(1, 'Laptop HP envy', '20000000', '/img/product/giaydep.jpg@/img/product/giaydep.jpg@/img/product/giaydep.jpg@/img/product/giaydep.jpg', 'Sản phẩm máy tính laptop văn phòng do hãng HP sản xuất', '2kg', 2, 5, 4.3, 10, 1, 25),
(2, 'Latop HP pavilion', '15000000', '/img/product/giaydep.jpg@/img/product/giaydep.jpg@/img/product/giaydep.jpg@/img/product/giaydep.jpg', 'Sản phẩm máy tính laptop văn phòng do hãng HP sản xuất', '1.5kg', 2, 5, 4.4, 10, 1, 0),
(3, 'Laptop Asus ROG', '35000000', '/img/product/giaydep.jpg@/img/product/giaydep.jpg@/img/product/giaydep.jpg@/img/product/giaydep.jpg', 'Sản phẩm laptop gaming của hãng Asus', '3kg', 3, 10, 4.5, 10, 1, 20),
(4, 'Điện thoại Asus Zenfone max', '5000000', '/img/product/giaydep.jpg@/img/product/giaydep.jpg@/img/product/giaydep.jpg@/img/product/giaydep.jpg', 'Điện thoại smartphone thế hệ thứ 5 do hãng Asus sản xuất', '200mg', 1, 10, 4, 10, 1, 0);

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
  `id` int(11) NOT NULL,
  `id_customer` int(11) NOT NULL,
  `id_product` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `product_order`
--

CREATE TABLE `product_order` (
  `id_order` int(11) NOT NULL,
  `id_customer` int(11) NOT NULL,
  `id_shop` int(11) NOT NULL,
  `date_order` bigint(20) NOT NULL,
  `state` int(11) NOT NULL,
  `type_transport` int(11) NOT NULL,
  `type_payment` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `promotion`
--

CREATE TABLE `promotion` (
  `id_promotion` int(11) NOT NULL,
  `name_promotion` varchar(20) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `promotion_details`
--

CREATE TABLE `promotion_details` (
  `id_promotion` int(11) NOT NULL,
  `id_product` int(11) NOT NULL,
  `start_date` bigint(20) NOT NULL,
  `end_date` bigint(20) NOT NULL,
  `percent` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

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
(1, 'truong@gmail.com', 'acv', 'acv', 2, 'sdasd'),
(1, 'truong1@gmail.com', 'dasd', 'dasda', 4, 'dasdasd'),
(2, 'truong@gmail.com', 'dasdad', 'dasdad', 4, 'dasdad'),
(3, 'truong@gmail.com', 'San pham tot', 'okeeeee', 4.5, '24/03/2018');

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
(3, 'Hp Shop', 'Make it matter', '/img/shop/hp.png', '/img/shop/giaydep.jpg', 2, 'abc', '10234', 'abc.com', 4.5, 1),
(4, 'Asus Shop', 'In search of incredible', '/img/shop/asus.png', '/img/shop/giaydep.jpg', 5, 'abc', '12345', 'abc.com', 4.4, 1);

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
  `id` int(11) NOT NULL,
  `id_shop` int(11) NOT NULL,
  `id_user` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

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
  `image` text COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `type_child`
--

INSERT INTO `type_child` (`id_type_child`, `name_type_child`, `id_type_parent`, `image`) VALUES
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
(39, 'Phụ kiện máy ảnh', 11, '/img/category/child_phukienmayanh.png'),
(40, 'Phụ kiện bếp', 11, 'abc'),
(41, 'aaa', 1, 'abc');

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
(2, 'truong', 'truong@gmail.com', '123', 'Nha N1 chung cu Dong Tau@Thinh Liet@Hoang Mai@hello world@abc@1', '', '098776', 0, '', 2, 0),
(5, 'abc', 'truong1@gmail.com', '1', 'Nha N1 chung cu Dong Tau@Quan Hoang Mai@Ha Noi', '123', '2312', 0, 'dsads', 2, 0),
(11, 'vu xuan truong', 'a@gmail.com', '123', 'Nha N1 chung cu Dong Tau@Quan Hoang Mai@Ha Noi', '', '123', 0, '', 2, 0);

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
  ADD KEY `fk_order_product` (`id_product`);

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
  ADD KEY `fk_type_product` (`type_product`);

--
-- Indexes for table `product_details`
--
ALTER TABLE `product_details`
  ADD KEY `fk_product_details` (`id_product`);

--
-- Indexes for table `product_like`
--
ALTER TABLE `product_like`
  ADD PRIMARY KEY (`id`,`id_customer`,`id_product`),
  ADD KEY `fk_product_like` (`id_product`),
  ADD KEY `fk_customer_like` (`id_customer`);

--
-- Indexes for table `product_order`
--
ALTER TABLE `product_order`
  ADD PRIMARY KEY (`id_order`),
  ADD KEY `fk_order_shop` (`id_shop`),
  ADD KEY `fk_order_customer` (`id_customer`);

--
-- Indexes for table `promotion`
--
ALTER TABLE `promotion`
  ADD PRIMARY KEY (`id_promotion`);

--
-- Indexes for table `promotion_details`
--
ALTER TABLE `promotion_details`
  ADD PRIMARY KEY (`id_promotion`,`id_product`),
  ADD KEY `fk_product` (`id_product`);

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
  ADD PRIMARY KEY (`id`,`id_shop`,`id_user`),
  ADD KEY `fk_shop_follow` (`id_shop`),
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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `popular_search`
--
ALTER TABLE `popular_search`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id_product` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `product_like`
--
ALTER TABLE `product_like`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `product_order`
--
ALTER TABLE `product_order`
  MODIFY `id_order` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `promotion`
--
ALTER TABLE `promotion`
  MODIFY `id_promotion` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `shop`
--
ALTER TABLE `shop`
  MODIFY `id_shop` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT for table `shop_details`
--
ALTER TABLE `shop_details`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `shop_follow`
--
ALTER TABLE `shop_follow`
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
  MODIFY `id_type_child` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;
--
-- AUTO_INCREMENT for table `type_parent`
--
ALTER TABLE `type_parent`
  MODIFY `id_type_parent` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
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
  ADD CONSTRAINT `fk_order_details` FOREIGN KEY (`id_order`) REFERENCES `product_order` (`id_order`),
  ADD CONSTRAINT `fk_order_product` FOREIGN KEY (`id_product`) REFERENCES `product` (`id_product`);

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `fk_brand` FOREIGN KEY (`brand`) REFERENCES `brand` (`id_brand`),
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
  ADD CONSTRAINT `fk_order_customer` FOREIGN KEY (`id_customer`) REFERENCES `user` (`id_user`),
  ADD CONSTRAINT `fk_order_shop` FOREIGN KEY (`id_shop`) REFERENCES `shop` (`id_shop`);

--
-- Constraints for table `promotion_details`
--
ALTER TABLE `promotion_details`
  ADD CONSTRAINT `fk_product` FOREIGN KEY (`id_product`) REFERENCES `product` (`id_product`),
  ADD CONSTRAINT `fk_promotion` FOREIGN KEY (`id_promotion`) REFERENCES `promotion` (`id_promotion`);

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
