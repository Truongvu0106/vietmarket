<!DOCTYPE html>
<html>

<head>
    <?php 
        include_once("../../config.php");
        include("header.html"); 
    ?>
</head>
<body class="flat-blue">
    <div class="app-container">
        <div class="row content-container">
            <nav id="navbar" class="navbar navbar-default navbar-fixed-top navbar-top">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <button type="button" class="navbar-expand-toggle">
                            <i class="fa fa-bars icon"></i>
                        </button>
                        <ol class="breadcrumb navbar-breadcrumb">
                            <li>Dashboard</li>
                            <li class="active">
                                <?php 
                                    switch ($_GET["page"]) {
                                        case 'order':
                                            echo "Đơn hàng";
                                            break;
                                        case 'payment':
                                            echo "Thanh toán";
                                            break;
                                        case 'transport':
                                            echo "Vận chuyển";
                                            break;
                                        case 'banner':
                                            echo "Banner";
                                            break;
                                        case 'brand':
                                            echo "Thương hiệu";
                                            break;
                                        case 'discount':
                                            echo "Khuyến mại";
                                            break;
                                        case 'category':
                                            echo "Danh mục";
                                            break;
                                        case 'product':
                                            echo "Sản phẩm";
                                            break;
                                        case 'user':
                                            echo "Danh sách người dùng";
                                            break;
                                        default:
                                            # code...
                                            break;
                                    }
                                 ?>
                            </li>
                        </ol>
                        <button type="button" class="navbar-right-expand-toggle pull-right visible-xs">
                            <i class="fa fa-th icon"></i>
                        </button>
                    </div>
                    <ul class="nav navbar-nav navbar-right">
                        <button type="button" class="navbar-right-expand-toggle pull-right visible-xs">
                            <i class="fa fa-times icon"></i>
                        </button>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-comments-o"></i></a>
                            <ul class="dropdown-menu animated fadeInDown">
                                <li class="title">
                                    Notification <span class="badge pull-right">0</span>
                                </li>
                                <li class="message">
                                    No new notification
                                </li>
                            </ul>
                        </li>
                        <li class="dropdown danger">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-star-half-o"></i> 4</a>
                            <ul class="dropdown-menu danger  animated fadeInDown">
                                <li class="title">
                                    Notification <span class="badge pull-right">4</span>
                                </li>
                                <li>
                                    <ul class="list-group notifications">
                                        <a href="#">
                                            <li class="list-group-item">
                                                <span class="badge">1</span> <i class="fa fa-exclamation-circle icon"></i> new registration
                                            </li>
                                        </a>
                                        <a href="#">
                                            <li class="list-group-item">
                                                <span class="badge success">1</span> <i class="fa fa-check icon"></i> new orders
                                            </li>
                                        </a>
                                        <a href="#">
                                            <li class="list-group-item">
                                                <span class="badge danger">2</span> <i class="fa fa-comments icon"></i> customers messages
                                            </li>
                                        </a>
                                        <a href="#">
                                            <li class="list-group-item message">
                                                view all
                                            </li>
                                        </a>
                                    </ul>
                                </li>
                            </ul>
                        </li>
                        <li class="dropdown profile">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Emily Hart <span class="caret"></span></a>
                            <ul class="dropdown-menu animated fadeInDown">
                                <li class="profile-img">
                                    <img src="../../img/profile/picjumbo.com_HNCK4153_resize.jpg" class="profile-img">
                                </li>
                                <li>
                                    <div class="profile-info">
                                        <h4 class="username">Emily Hart</h4>
                                        <p>emily_hart@email.com</p>
                                        <div class="btn-group margin-bottom-2x" role="group">
                                            <button type="button" class="btn btn-default"><i class="fa fa-user"></i> Profile</button>
                                            <button type="button" class="btn btn-default"><i class="fa fa-sign-out"></i> Logout</button>
                                        </div>
                                    </div>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </nav>
            
            <!-- </div>  -->
            <?php 
                include("sidebar.html");
            ?>
            <div class="container-fluid">
                <div class="side-body">
                    <?php 
                        switch ($_GET["page"]) {
                            case 'order':
                                include("bussiness_view/order.php");
                                break;
                            case 'payment':
                                include("bussiness_view/payment.php");
                                break;
                            case 'transport':
                                include("bussiness_view/transport.php");
                                break;
                            case 'banner':
                                include("other_view/banner.php");
                                break;
                            case 'brand':
                                include("other_view/brand.php");
                                break;
                            case 'discount':
                                include("other_view/discount.php");
                                break;
                            case 'category':
                                include("product_view/category.php");
                                break;
                            case 'product':
                                include("product_view/product.php");
                                break;
                            case 'user':
                                include("user_view/user.php");
                                break;
                            default:
                                # code...
                                break;
                        }
                     ?>
                </div>
            </div>
        </div>
        <footer class="app-footer">
            <div class="wrapper">
                <span class="pull-right"><a href="#"><i class="fa fa-long-arrow-up"></i></a></span> 
            </div>
        </footer>
    <div>
    <?php include("footer.html") ?>
</body>

</html>