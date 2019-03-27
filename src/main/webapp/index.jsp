<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:requestEncoding value="UTF-8"/>

<c:choose>
    <c:when test="${not empty requestScope.get('lang')}">
        <fmt:setLocale value="${requestScope.get('lang')}"/>
    </c:when>
    <c:otherwise>
        <fmt:setLocale value="${cookie['locale'].value}"/>
    </c:otherwise>
</c:choose>

<fmt:setBundle basename="language" var="bundle" scope="application"/>
<html>
<!--<![endif]-->

<head>

    <title><fmt:message key="title.index" bundle="${bundle}" /></title>
    <meta charset="UTF-8"/>
    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <title>HTML Template</title>

    <!-- Google font -->
    <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700%7CVarela+Round" rel="stylesheet">

    <link rel="shortcut icon" href="site/images/logo_icon.png">

    <!-- Bootstrap -->
    <link type="text/css" rel="stylesheet" href="site/css/bootstrap.min.css" />

    <!-- Owl Carousel -->
    <link type="text/css" rel="stylesheet" href="site/css/owl.carousel.css" />
    <link type="text/css" rel="stylesheet" href="site/css/owl.theme.default.css" />

    <!-- Magnific Popup -->
    <link type="text/css" rel="stylesheet" href="site/css/magnific-popup.css" />

    <!-- Font Awesome Icon -->
    <link rel="stylesheet" href="site/css/font-awesome.min.css">

    <!-- Custom stlylesheet -->
    <link type="text/css" rel="stylesheet" href="site/css/style.css" />

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>

<body>
<!-- Header -->
<header id="home">
    <!-- Background Image -->
    <div class="bg-img" style="background-image: url('site/img/background1.jpg');">
        <div class="overlay"></div>
    </div>
    <!-- /Background Image -->

    <!-- Nav -->
    <nav id="nav" class="navbar nav-transparent">
        <div class="container">

            <div class="navbar-header">
                <!-- Logo -->
                <div class="navbar-brand">
                    <a href="${pageContext.request.contextPath}/index.jsp">
                        <img src="${pageContext.request.contextPath}/site/img/mt-logo.png" alt="mytunes">
                    </a>
                </div>
                <!-- /Logo -->

                <!-- Collapse nav button -->
                <div class="nav-collapse">
                    <span></span>
                </div>
                <!-- /Collapse nav button -->
            </div>

            <!--  Main navigation  -->
            <ul class="main-nav nav navbar-nav navbar-right">
                <li><a href="#about"><fmt:message key="index.about" bundle="${bundle}" /></a></li>
                <li><a href="#service"><fmt:message key="index.service" bundle="${bundle}" /></a></li>
                <li><a href="#pricing"><fmt:message key="index.price" bundle="${bundle}" /></a></li>
                <%--<li class="has-dropdown"><a href="#blog"><fmt:message key="index.news" bundle="${bundle}" /></a>--%>
                    <%--<ul class="dropdown">--%>
                        <%--<li><a href="${pageContext.request.contextPath}/site/blog-single.html"><fmt:message key="index.post" bundle="${bundle}" /></a></li>--%>
                    <%--</ul>--%>
                <%--</li>--%>
                <li><a href="#contact"><fmt:message key="index.contact" bundle="${bundle}" /></a></li>

                <li class="has-dropdown"><a href="#blog"><fmt:message key="index.language" bundle="${bundle}" /></a>
                    <ul class="dropdown">
                        <li><a href="${pageContext.request.contextPath}/crud?command=changeLang&from=/&lang=ru">Русский</a></li>
                        <li><a href="${pageContext.request.contextPath}/crud?command=changeLang&from=/&lang=en">English</a></li>
                    </ul>
                </li>
            </ul>
            <!-- /Main navigation -->

        </div>
    </nav>
    <!-- /Nav -->

    <!-- home wrapper -->
    <div class="home-wrapper">
        <div class="container">
            <div class="row">

                <!-- home content -->
                <div class="col-md-10 col-md-offset-1">
                    <div class="home-content">
                        <h1 class="white-text">MyTunes - <fmt:message key="index.musicalportal" bundle="${bundle}" /> </h1>
                        <p class="white-text"><fmt:message key="index.wemademusic" bundle="${bundle}" />
                        </p>
                        <form action="${pageContext.request.contextPath}/crud" method="post">
                            <button class="white-btn" name="command" value="viewSignInPage"><fmt:message key="index.login" bundle="${bundle}" /></button>
                            <button class="main-btn" name="command" value="viewSignUpPage"><fmt:message key="index.register" bundle="${bundle}" /></button>
                        </form>
                    </div>
                </div>
                <!-- /home content -->

            </div>
        </div>
    </div>
    <!-- /home wrapper -->

</header>
<!-- /Header -->

<!-- About -->
<div id="about" class="section md-padding">

    <!-- Container -->
    <div class="container">

        <!-- Row -->
        <div class="row">

            <!-- Section header -->
            <div class="section-header text-center">
                <h2 class="title"><fmt:message key="index.welcome" bundle="${bundle}" /></h2>
            </div>
            <!-- /Section header -->

            <!-- about -->
            <div class="col-md-4">
                <div class="about">
                    <i class="fa fa-cogs"></i>
                    <h3><fmt:message key="index.flexibility" bundle="${bundle}" /></h3>
                    <p><fmt:message key="index.msg.flexibility" bundle="${bundle}" /></p>
                </div>
            </div>
            <!-- /about -->

            <!-- about -->
            <div class="col-md-4">
                <div class="about">
                    <i class="fa fa-magic"></i>
                    <h3><fmt:message key="index.features" bundle="${bundle}" /></h3>
                    <p><fmt:message key="index.msg.features" bundle="${bundle}" /></p>
                </div>
            </div>
            <!-- /about -->

            <!-- about -->
            <div class="col-md-4">
                <div class="about">
                    <i class="fa fa-mobile"></i>
                    <h3><fmt:message key="index.support" bundle="${bundle}" /></h3>
                    <p><fmt:message key="index.msg.support" bundle="${bundle}" /></p>
                </div>
            </div>
            <!-- /about -->

        </div>
        <!-- /Row -->

    </div>
    <!-- /Container -->

</div>
<!-- /About -->

<!-- Service -->
<div id="service" class="section md-padding">

    <!-- Container -->
    <div class="container">

        <!-- Row -->
        <div class="row">

            <!-- Section header -->
            <div class="section-header text-center">
                <h2 class="title"><fmt:message key="index.offers" bundle="${bundle}" /></h2>
            </div>
            <!-- /Section header -->

            <!-- service -->
            <div class="col-md-4 col-sm-6">
                <div class="service">
                    <i class="fa fa-diamond"></i>
                    <h3><fmt:message key="index.composition" bundle="${bundle}" /></h3>
                    <p><fmt:message key="inde.msg.composition" bundle="${bundle}" /></p>
                </div>
            </div>
            <!-- /service -->

            <!-- service -->
            <div class="col-md-4 col-sm-6">
                <div class="service">
                    <i class="fa fa-cogs"></i>
                    <h3><fmt:message key="index.album" bundle="${bundle}" /></h3>
                    <p><fmt:message key="index.msg.album" bundle="${bundle}" /></p>
                </div>
            </div>
            <!-- /service -->

            <!-- service -->
            <div class="col-md-4 col-sm-6">
                <div class="service">
                    <i class="fa fa-diamond"></i>
                    <h3><fmt:message key="index.musicselection" bundle="${bundle}" /></h3>
                    <p><fmt:message key="index.msg.musicselection" bundle="${bundle}" /></p>
                </div>
            </div>
            <!-- /service -->

            <!-- service -->
            <div class="col-md-4 col-sm-6">
                <div class="service">
                    <i class="fa fa-rocket"></i>
                    <h3><fmt:message key="index.feedback" bundle="${bundle}" /></h3>
                    <p><fmt:message key="index.msg.feedback" bundle="${bundle}" /></p>
                </div>
            </div>
            <!-- /service -->

            <!-- service -->
            <div class="col-md-4 col-sm-6">
                <div class="service">
                    <i class="fa fa-pencil"></i>
                    <h3><fmt:message key="index.community" bundle="${bundle}" /></h3>
                    <p><fmt:message key="index.msg.community" bundle="${bundle}" /></p>
                </div>
            </div>
            <!-- /service -->

            <!-- service -->
            <div class="col-md-4 col-sm-6">
                <div class="service">
                    <i class="fa fa-flask"></i>
                    <h3><fmt:message key="index.cost" bundle="${bundle}" /></h3>
                    <p><fmt:message key="index.msg.cost" bundle="${bundle}" /></p>
                </div>
            </div>
            <!-- /service -->

        </div>
        <!-- /Row -->

    </div>
    <!-- /Container -->

</div>
<!-- /Service -->


<!-- Why Choose Us -->
<div id="features" class="section md-padding bg-grey">

    <!-- Container -->
    <div class="container">

        <!-- Row -->
        <div class="row">

            <!-- why choose us content -->
            <div class="col-md-6">
                <div class="section-header">
                    <h2 class="title"><fmt:message key="index.whychoiseus" bundle="${bundle}" /></h2>
                </div>
                <p><fmt:message key="index.msg.whychoiseus" bundle="${bundle}" /></p>
                <div class="feature">
                    <i class="fa fa-check"></i>
                    <p><fmt:message key="index.msg.longinweb" bundle="${bundle}" /></p>
                </div>
                <div class="feature">
                    <i class="fa fa-check"></i>
                    <p><fmt:message key="index.msg.parthner" bundle="${bundle}" /></p>
                </div>
                <div class="feature">
                    <i class="fa fa-check"></i>
                    <p><fmt:message key="index.msg.soundquality" bundle="${bundle}" /></p>
                </div>
            </div>
            <!-- /why choose us content -->

            <!-- About slider -->
            <div class="col-md-6">
                <div id="about-slider" class="owl-carousel owl-theme" style="display: run-in">
                    <img class="img-responsive" src="site/img/about1.jpg" alt="">
                    <img class="img-responsive" src="site/img/about2.jpg" alt="">
                    <img class="img-responsive" src="site/img/about1.jpg" alt="">
                </div>
            </div>
            <!-- /About slider -->

        </div>
        <!-- /Row -->

    </div>
    <!-- /Container -->

</div>
<!-- /Why Choose Us -->


<!-- Numbers -->
<div id="numbers" class="section sm-padding">

    <!-- Background Image -->
    <div class="bg-img" style="background-image: url('${pageContext.request.contextPath}/site/img/background2.jpg');">
        <div class="overlay"></div>
    </div>
    <!-- /Background Image -->

    <!-- Container -->
    <div class="container">

        <!-- Row -->
        <div class="row">

            <!-- number -->
            <div class="col-sm-3 col-xs-6">
                <div class="number">
                    <i class="fa fa-users"></i>
                    <h3 class="white-text"><span class="counter"><fmt:message key="index.container.many" bundle="${bundle}" /></span></h3>
                    <span class="white-text"><fmt:message key="index.container.happyclient" bundle="${bundle}" /></span>
                </div>
            </div>
            <!-- /number -->

            <!-- number -->
            <div class="col-sm-3 col-xs-6">
                <div class="number">
                    <i class="fa fa-trophy"></i>
                    <h3 class="white-text"><span class="counter"><fmt:message key="index.container.alot" bundle="${bundle}" /></span></h3>
                    <span class="white-text"><fmt:message key="index.container.wons" bundle="${bundle}" /></span>
                </div>
            </div>
            <!-- /number -->

            <!-- number -->
            <div class="col-sm-3 col-xs-6">
                <div class="number">
                    <i class="fa fa-coffee"></i>
                    <h3 class="white-text"><span class="counter">99</span></h3>
                    <span class="white-text"><fmt:message key="index.container.coffees" bundle="${bundle}" /></span>
                </div>
            </div>
            <!-- /number -->

            <!-- number -->
            <div class="col-sm-3 col-xs-6">
                <div class="number">
                    <i class="fa fa-file"></i>
                    <h3 class="white-text"><span class="counter">15</span></h3>
                    <span class="white-text"><fmt:message key="index.container.days" bundle="${bundle}" /></span>
                </div>
            </div>
            <!-- /number -->

        </div>
        <!-- /Row -->

    </div>
    <!-- /Container -->

</div>
<!-- /Numbers -->

<!-- Pricing -->
<div id="pricing" class="section md-padding">

    <!-- Container -->
    <div class="container">

        <!-- Row -->
        <div class="row">

            <!-- Section header -->
            <div class="section-header text-center">
                <h2 class="title"><fmt:message key="index.pricing.table" bundle="${bundle}" /></h2>
            </div>
            <!-- /Section header -->

            <!-- pricing -->
            <div class="col-sm-4">
                <div class="pricing">
                    <div class="price-head">
                        <span class="price-title"><fmt:message key="index.pricing.composition" bundle="${bundle}" /></span>
                        <div class="price">
                            <h3>~$1<span class="duration">/ <fmt:message key="index.pricing.song" bundle="${bundle}" /></span></h3>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /pricing -->

            <!-- pricing -->
            <div class="col-sm-4">
                <div class="pricing">
                    <div class="price-head">
                        <span class="price-title"><fmt:message key="index.pricing.album" bundle="${bundle}" /></span>
                        <div class="price">
                            <h3>~$3<span class="duration">/ <fmt:message key="index.pricing.albumcost" bundle="${bundle}" /></span></h3>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /pricing -->

            <!-- pricing -->
            <div class="col-sm-4">
                <div class="pricing">
                    <div class="price-head">
                        <span class="price-title"><fmt:message key="index.pricing.musicselectioncost" bundle="${bundle}" /></span>
                        <div class="price">
                            <h3>~$10<span class="duration">/ <fmt:message key="index.pricing.selection" bundle="${bundle}" /></span></h3>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /pricing -->

        </div>
        <!-- Row -->

    </div>
    <!-- /Container -->

</div>
<!-- /Pricing -->


<!-- Testimonial -->
<div id="testimonial" class="section md-padding">

    <!-- Background Image -->
    <div class="bg-img" style="background-image: url('${pageContext.request.contextPath}/site/img/background3.jpg');">
        <div class="overlay"></div>
    </div>
    <!-- /Background Image -->

</div>
<!-- /Testimonial -->

<!-- Contact -->
<div id="contact" class="section md-padding">

    <!-- Container -->
    <div class="container">

        <!-- Row -->
        <div class="row">

            <!-- Section-header -->
            <div class="section-header text-center">
                <h2 class="title"><li><a><fmt:message key="index.getintouch" bundle="${bundle}" /></li></h2>
            </div>
            <!-- /Section-header -->

            <!-- contact -->
            <div class="col-sm-4">
                <div class="contact">
                    <i class="fa fa-phone"></i>
                    <h3><li><fmt:message key="index.phone" bundle="${bundle}" /></li></h3>
                    <p>+375-25-7261864</p>
                </div>
            </div>
            <!-- /contact -->

            <!-- contact -->
            <div class="col-sm-4">
                <div class="contact">
                    <i class="fa fa-envelope"></i>
                    <h3>Email</h3>
                    <p>mytunessupp@gmail.com</p>
                </div>
            </div>
            <!-- /contact -->

            <!-- contact -->
            <div class="col-sm-4">
                <div class="contact">
                    <i class="fa fa-map-marker"></i>
                    <h3><fmt:message key="index.address" bundle="${bundle}" /></h3>
                    <p><fmt:message key="index.msg.address" bundle="${bundle}" /></p>
                </div>
            </div>
            <!-- /contact -->

            <!-- contact form -->
            <div class="col-md-8 col-md-offset-2">
                <form class="contact-form">
                    <input type="text" class="input" placeholder="<fmt:message key="index.placeholder.name" bundle="${bundle}" />">
                    <input type="email" class="input" placeholder="Email">
                    <input type="text" class="input" placeholder="<fmt:message key="index.placeholder.subject" bundle="${bundle}" />">
                    <textarea class="input" placeholder="<fmt:message key="index.placeholder.message" bundle="${bundle}" />"></textarea>
                    <button class="main-btn"><fmt:message key="index.msg.sendemail" bundle="${bundle}" /></button>
                </form>
            </div>
            <!-- /contact form -->

        </div>
        <!-- /Row -->

    </div>
    <!-- /Container -->

</div>
<!-- /Contact -->


<!-- Footer -->
<footer id="footer" class="sm-padding bg-dark">

    <!-- Container -->
    <div class="container">

        <!-- Row -->
        <div class="row">

            <div class="col-md-12">

                <!-- footer logo -->
                <%--<div class="footer-logo">--%>
                    <%--<a href="${pageContext.request.contextPath}/index.jsp"><img src="site/img/mt-logo.png" alt="logo"  style="max-height: 200px; width: 200px; height: 200px"></a>--%>
                <%--</div>--%>
                <!-- /footer logo -->

                <%--<!-- footer follow -->--%>
                <%--<ul class="footer-follow">--%>
                    <%--<li><a href="#"><i class="fa fa-facebook" style="margin-top: 30%"></i></a></li>--%>
                    <%--<li><a href="#"><i class="fa fa-twitter" style="margin-top: 30%"></i></a></li>--%>
                    <%--<li><a href="#"><i class="fa fa-google-plus" style="margin-top: 30%"></i></a></li>--%>
                    <%--<li><a href="#"><i class="fa fa-instagram" style="margin-top: 30%"></i></a></li>--%>
                    <%--<li><a href="#"><i class="fa fa-linkedin" style="margin-top: 30%"></i></a></li>--%>
                    <%--<li><a href="#"><i class="fa fa-vk" style="margin-top: 30%"></i></a></li>--%>
                <%--</ul>--%>
                <%--<!-- /footer follow -->--%>

                <!-- footer copyright -->
                <div class="footer-copyright">
                    <p><fmt:message key="index.footer.copyrighr" bundle="${bundle}" /></p>
                </div>
                <!-- /footer copyright -->

            </div>

        </div>
        <!-- /Row -->

    </div>
    <!-- /Container -->

</footer>
<!-- /Footer -->

<!-- Back to top -->
<div id="back-to-top"></div>
<!-- /Back to top -->

<!-- Preloader -->

<!-- /Preloader -->

<!-- jQuery Plugins -->
<script type="text/javascript" src="${pageContext.request.contextPath}/site/js/jquery.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/site/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/site/js/owl.carousel.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/site/js/jquery.magnific-popup.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/site/js/main.js"></script>

</body>

</html>
