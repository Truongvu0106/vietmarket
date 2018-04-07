<!DOCTYPE html>
<html>

<head>
    <title>Flat Admin V.2 - Free Bootstrap Admin Templates</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- Fonts -->
    <link href='http://fonts.googleapis.com/css?family=Roboto+Condensed:300,400' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Lato:300,400,700,900' rel='stylesheet' type='text/css'>
    <!-- CSS Libs -->
    <link rel="stylesheet" type="text/css" href="../../lib/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="../../lib/css/font-awesome.min.css">
    <link rel="stylesheet" type="text/css" href="../../lib/css/animate.min.css">
    <link rel="stylesheet" type="text/css" href="../../lib/css/bootstrap-switch.min.css">
    <link rel="stylesheet" type="text/css" href="../../lib/css/checkbox3.min.css">
    <link rel="stylesheet" type="text/css" href="../../lib/css/jquery.dataTables.min.css">
    <link rel="stylesheet" type="text/css" href="../../lib/css/dataTables.bootstrap.css">
    <link rel="stylesheet" type="text/css" href="../../lib/css/select2.min.css">
    <!-- CSS App -->
    <link rel="stylesheet" type="text/css" href="../../css/style.css">
    <link rel="stylesheet" type="text/css" href="../../css/themes/flat-blue.css">
</head>

<body class="flat-blue">
    <div class="app-container">
        <div class="row content-container">
            <nav class="navbar navbar-default navbar-fixed-top navbar-top">
                <div class="container-fluid">
                    <div class="navbar-header">
                        <button type="button" class="navbar-expand-toggle">
                            <i class="fa fa-bars icon"></i>
                        </button>
                        <ol class="breadcrumb navbar-breadcrumb">
                            <li>UI Kits</li>
                            <li class="active">Grid</li>
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
            <?php 
                include("sidebar.html");
            ?>
            <!-- Main Content -->
            <div class="container-fluid">
                <div class="side-body">
                    <div class="page-title">
                        <span class="title">Grid</span>
                        <div class="description">Bootstrap Grid Systems</div>
                    </div>
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="card">
                                <div class="card-header">
                                    <div class="card-title">
                                        <div class="title">Grid Example</div>
                                    </div>
                                </div>
                                <div class="card-body">
                                    <div>
                                        <p>See how aspects of the Bootstrap grid system work across multiple devices with a handy table.</p>
                                        <div class="table-responsive">
                                            <table class="table table-bordered table-striped">
                                                <thead>
                                                    <tr>
                                                        <th></th>
                                                        <th>
                                                            Extra small devices
                                                            <small>Phones (&lt;768px)</small>
                                                        </th>
                                                        <th>
                                                            Small devices
                                                            <small>Tablets (≥768px)</small>
                                                        </th>
                                                        <th>
                                                            Medium devices
                                                            <small>Desktops (≥992px)</small>
                                                        </th>
                                                        <th>
                                                            Large devices
                                                            <small>Desktops (≥1200px)</small>
                                                        </th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr>
                                                        <th class="text-nowrap" scope="row">Grid behavior</th>
                                                        <td>Horizontal at all times</td>
                                                        <td colspan="3">Collapsed to start, horizontal above breakpoints</td>
                                                    </tr>
                                                    <tr>
                                                        <th class="text-nowrap" scope="row">Container width</th>
                                                        <td>None (auto)</td>
                                                        <td>750px</td>
                                                        <td>970px</td>
                                                        <td>1170px</td>
                                                    </tr>
                                                    <tr>
                                                        <th class="text-nowrap" scope="row">Class prefix</th>
                                                        <td><code>.col-xs-</code></td>
                                                        <td><code>.col-sm-</code></td>
                                                        <td><code>.col-md-</code></td>
                                                        <td><code>.col-lg-</code></td>
                                                    </tr>
                                                    <tr>
                                                        <th class="text-nowrap" scope="row"># of columns</th>
                                                        <td colspan="4">12</td>
                                                    </tr>
                                                    <tr>
                                                        <th class="text-nowrap" scope="row">Column width</th>
                                                        <td class="text-muted">Auto</td>
                                                        <td>~62px</td>
                                                        <td>~81px</td>
                                                        <td>~97px</td>
                                                    </tr>
                                                    <tr>
                                                        <th class="text-nowrap" scope="row">Gutter width</th>
                                                        <td colspan="4">30px (15px on each side of a column)</td>
                                                    </tr>
                                                    <tr>
                                                        <th class="text-nowrap" scope="row">Nestable</th>
                                                        <td colspan="4">Yes</td>
                                                    </tr>
                                                    <tr>
                                                        <th class="text-nowrap" scope="row">Offsets</th>
                                                        <td colspan="4">Yes</td>
                                                    </tr>
                                                    <tr>
                                                        <th class="text-nowrap" scope="row">Column ordering</th>
                                                        <td colspan="4">Yes</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                    <div class="sub-title">Example </div>
                                    <div>
                                        <!-- Stack the columns on mobile by making one full-width and the other half-width -->
                                        <div class="row example">
                                            <div class="col-xs-12 col-md-8">
                                                <div>.col-xs-12 .col-md-8</div>
                                            </div>
                                            <div class="col-xs-6 col-md-4">
                                                <div>.col-xs-6 .col-md-4</div>
                                            </div>
                                        </div>
                                        <!-- Columns start at 50% wide on mobile and bump up to 33.3% wide on desktop -->
                                        <div class="row example">
                                            <div class="col-xs-6 col-md-4">
                                                <div>.col-xs-6 .col-md-4</div>
                                            </div>
                                            <div class="col-xs-6 col-md-4">
                                                <div>.col-xs-6 .col-md-4</div>
                                            </div>
                                            <div class="col-xs-6 col-md-4">
                                                <div>.col-xs-6 .col-md-4</div>
                                            </div>
                                        </div>
                                        <!-- Columns are always 50% wide, on mobile and desktop -->
                                        <div class="row example">
                                            <div class="col-xs-6">
                                                <div>.col-xs-6</div>
                                            </div>
                                            <div class="col-xs-6">
                                                <div>.col-xs-6</div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="sub-title">No Gap between Column <span class="description">( with .no-gap class )</span></div>
                                    <div>
                                        <!-- Stack the columns on mobile by making one full-width and the other half-width -->
                                        <div class="row no-gap example">
                                            <div class="col-xs-12 col-md-8">
                                                <div>.col-xs-12 .col-md-8</div>
                                            </div>
                                            <div class="col-xs-6 col-md-4">
                                                <div>.col-xs-6 .col-md-4</div>
                                            </div>
                                        </div>
                                        <!-- Columns start at 50% wide on mobile and bump up to 33.3% wide on desktop -->
                                        <div class="row no-gap example">
                                            <div class="col-xs-6 col-md-4">
                                                <div>.col-xs-6 .col-md-4</div>
                                            </div>
                                            <div class="col-xs-6 col-md-4">
                                                <div>.col-xs-6 .col-md-4</div>
                                            </div>
                                            <div class="col-xs-6 col-md-4">
                                                <div>.col-xs-6 .col-md-4</div>
                                            </div>
                                        </div>
                                        <!-- Columns are always 50% wide, on mobile and desktop -->
                                        <div class="row no-gap example">
                                            <div class="col-xs-6">
                                                <div>.col-xs-6</div>
                                            </div>
                                            <div class="col-xs-6">
                                                <div>.col-xs-6</div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <footer class="app-footer">
            <div class="wrapper">
                <span class="pull-right">2.1 <a href="#"><i class="fa fa-long-arrow-up"></i></a></span> © 2015 Copyright.
            </div>
        </footer>
        <div>
            <!-- Javascript Libs -->
            <script type="text/javascript" src="../../lib/js/jquery.min.js"></script>
            <script type="text/javascript" src="../../lib/js/bootstrap.min.js"></script>
            <script type="text/javascript" src="../../lib/js/Chart.min.js"></script>
            <script type="text/javascript" src="../../lib/js/bootstrap-switch.min.js"></script>

            <script type="text/javascript" src="../../lib/js/jquery.matchHeight-min.js"></script>
            <script type="text/javascript" src="../../lib/js/jquery.dataTables.min.js"></script>
            <script type="text/javascript" src="../../lib/js/dataTables.bootstrap.min.js"></script>
            <script type="text/javascript" src="../../lib/js/select2.full.min.js"></script>
            <script type="text/javascript" src="../../lib/js/ace/ace.js"></script>
            <script type="text/javascript" src="../../lib/js/ace/mode-html.js"></script>
            <script type="text/javascript" src="../../lib/js/ace/theme-github.js"></script>
            <!-- Javascript -->
            <script type="text/javascript" src="../../js/app.js"></script>
            <script type="text/javascript" src="../../js/card.js"></script>
</body>

</html>
