<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">
    <title>Recyclothes</title>
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link href="css/prettyPhoto.css" rel="stylesheet">
    <link href="css/price-range.css" rel="stylesheet">
    <link href="css/animate.css" rel="stylesheet">
	<link href="css/main.css" rel="stylesheet">
	<link href="css/responsive.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="js/html5shiv.js"></script>
    <script src="js/respond.min.js"></script>
    <![endif]-->       
    <link rel="shortcut icon" href="images/ico/favicon.ico">
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="images/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="images/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72" href="images/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="images/ico/apple-touch-icon-57-precomposed.png">
</head><!--/head-->

<body>
<input id="id-txt-userAgent" type="hidden" value="<%request.getHeader("user-agent");%>">
	<header id="header"><!--header-->
		<div class="header_top"><!--header_top-->
			<div class="container">
				<div class="row">
					<div class="col-sm-6">
						<div class="contactinfo">
							<ul class="nav nav-pills">
								<li><a href="#"><i class="fa fa-phone"></i> +2 95 01 88 821</a></li>
								<li><a href="#"><i class="fa fa-envelope"></i> genesiscastillo@hotmail.com</a></li>
							</ul>
						</div>
					</div>
					<div class="col-sm-6">
						<div class="social-icons pull-right">
							<ul class="nav navbar-nav">
								<li><a href="#"><i class="fa fa-facebook"></i></a></li>
								<li><a href="#"><i class="fa fa-twitter"></i></a></li>
								<li><a href="#"><i class="fa fa-linkedin"></i></a></li>
								<li><a href="#"><i class="fa fa-dribbble"></i></a></li>
								<li><a href="#"><i class="fa fa-google-plus"></i></a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div><!--/header_top-->
		
	</header><!--/header-->
	<section id="advertisement">
		<div class="container">
			<div class="col-sm-4">
				<img src="img/caprichitos.jpg">
			</div>
			<div class="col-sm-8">
				<img src="img/marcas.jpg">
			</div>
		</div>
	</section>
	<section id="slider"><!--slider-->
		<div class="container">
			<div class="row">
				<div class="col-sm-12">
					<div id="slider-carousel" class="carousel slide" data-ride="carousel">
						<ol class="carousel-indicators">
							<li data-target="#slider-carousel" data-slide-to="0" class="active"></li>
							<li data-target="#slider-carousel" data-slide-to="1"></li>
							<li data-target="#slider-carousel" data-slide-to="2"></li>
						</ol>
						
						<div class="carousel-inner">
							<div class="item active">
								<div class="col-sm-6">
									<h1>RecyClothes</h1>
									<h2>Moda Infantil</h2>
									<p>Venta de ropa americana para bebes y niños, categoria PREMIUN (semi nuevo)</p>
								</div>
								<div class="col-sm-6">
									<img src="img/ropa1.jpg" width="80%;" />
								</div>
							</div>
							<div class="item">
								<div class="col-sm-6">
									<h1>RecyClothes</h1>
									<h2>Moda Infantil</h2>
									<p>Venta de ropa americana para bebes y niños, categoria PREMIUN (semi nuevo)</p>
								</div>
								<div class="col-sm-6">
									<img src="img/ropa2.JPG" width="80%;" />
								</div>
							</div>
							
							<div class="item">
								<div class="col-sm-6">
									<h1>RecyClothes</h1>
									<h2>Moda Infantil</h2>
									<p>Venta de ropa americana para bebes y niños, categoria PREMIUN (semi nuevo)</p>
								</div>
								<div class="col-sm-6">
									<img src="img/ropa3.JPG" width="80%;" />
								</div>
							</div>
						</div>
						
						<a href="#slider-carousel" class="left control-carousel hidden-xs" data-slide="prev">
							<i class="fa fa-angle-left"></i>
						</a>
						<a href="#slider-carousel" class="right control-carousel hidden-xs" data-slide="next">
							<i class="fa fa-angle-right"></i>
						</a>
					</div>
					
				</div>
			</div>
		</div>
	</section><!--/slider-->
	<div class="container" id="id-panel-central"></div>
	<footer id="footer"><!--Footer-->
		<div class="footer-top">
			<div class="container">
				<div class="row">
					<div class="col-sm-4">
						<div class="single-widget">
							<h2>Puntos de Entrega</h2>
							<ul class="nav nav-pills nav-stacked">
								<li>Metro Plaza de Armas</li>
								<li>Metro Cal y Canto</li>
							</ul>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="single-widget">
							<h2>ENVIOS A TODOS CHILE</h2>
							<ul class="nav nav-pills nav-stacked">
								<li></li>
							</ul>
						</div>
					</div>
					<div class="col-sm-4">
						<div class="single-widget">
							<h2>Servicios</h2>
							<ul class="nav nav-pills nav-stacked">
								<li><a href="#" id="id-href-como-comprar">Como Comprar</a></li>
								<li><a href="#" id="id-href-contacto">Contacto</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="footer-bottom">
			<div class="container">
				<div class="row">
					<p class="pull-left">Copyright © 2016 recyclothes.cl - Todos los derechos Reservados.</p>
				</div>
			</div>
		</div>
	</footer><!--/Footer-->
	<script src="js/jquery.js"></script>
	<script src="js/price-range.js"></script>
	<script src="js/jquery.scrollUp.min.js"></script>
	<script src="js/bootstrap.min.js"></script>
	<script src="js/jquery.prettyPhoto.js"></script>
	<script src="js/main.js"></script>
	<script src="js/bootbox.min.js"></script>
</body>
</html>