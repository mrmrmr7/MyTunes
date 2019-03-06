<!doctype html>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7" lang=""> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8" lang=""> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9" lang=""> <![endif]-->
<!--[if gt IE 8]><!-->
<html class="no-js" lang="en">
<!--<![endif]-->

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Sufee Admin - HTML5 Admin Template</title>
    <meta name="description" content="Sufee Admin - HTML5 Admin Template">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <link rel="apple-touch-icon" href="${pageContext.request.contextPath}/site/apple-icon.png">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/site/favicon.ico">


    <link rel="stylesheet" href="${pageContext.request.contextPath}/site/vendors/bootstrap/dist/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/site/vendors/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/site/vendors/themify-icons/css/themify-icons.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/site/vendors/flag-icon-css/css/flag-icon.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/site/vendors/selectFX/css/cs-skin-elastic.css">

    <link rel="stylesheet" href="${pageContext.request.contextPath}/site/assets/css/style.css">

    <link href='https://fonts.googleapis.com/css?family=Open+Sans:400,600,700,800' rel='stylesheet' type='text/css'>



</head>

<body class="bg-dark">


    <div class="sufee-login d-flex align-content-center flex-wrap">
        <div class="container">
            <div class="login-content">
                <div class="login-logo">
                    <a href="${pageContext.request.contextPath}/index.jsp">
                        <img class="align-content" src="${pageContext.request.contextPath}/site/images/logo.png" alt="">
                    </a>
                </div>
                <div class="login-form">
                    <script>
                        function formValidation() {
                            var x = document.forms["signInForm"]["login"].value;
                            if (x == "") {
                                document.getElementById("loginId").className = "is-invalid form-control";
                                document.getElementById("passwordId").className = "is-invalid form-control";
                                return false;
                            }
                        }
                    </script>
                    <form action="${pageContext.request.contextPath}/crud"  method="post" id="si" name="signInForm" onsubmit="return formValidation()">
                        <div class="form-group">
                            <label>Email address</label>
                            <input type="text" class="form-control" placeholder="Email" id="loginId" name="login" autofocus>
                        </div>
                        <div class="form-group">
                            <label>Password</label>
                            <input type="password" class="form-control" placeholder="Password" id="passwordId" name="password" required>
                        </div>
                        <div class="checkbox">
                            <label>
                                <input type="checkbox"> Remember Me
                            </label>
                            <label class="pull-right">
                                <a href="${pageContext.request.contextPath}/site#">Forgotten Password?</a>
                            </label>
                        </div>
                        <button type="submit" class="btn btn-success btn-flat m-b-30 m-t-30">Sign in</button>
                        <div class="social-login-content">
                            <div class="social-button">
                                <button type="button" class="btn social facebook btn-flat btn-addon mb-3"><i class="ti-facebook"></i>Sign in with facebook</button>
                                <button type="button" class="btn social twitter btn-flat btn-addon mt-2"><i class="ti-twitter"></i>Sign in with twitter</button>
                            </div>
                        </div>
                        <div class="register-link m-t-15 text-center">
                            <p>Don't have account ? <a href="${pageContext.request.contextPath}/site#"> Sign Up Here</a></p>
                        </div>
                        <input type="hidden" name="command" value="signIn">
                    </form>
                </div>
            </div>
        </div>
    </div>


    <script src="${pageContext.request.contextPath}/site/vendors/jquery/dist/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/site/vendors/popper.js/dist/umd/popper.min.js"></script>
    <script src="${pageContext.request.contextPath}/site/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
    <script src="${pageContext.request.contextPath}/site/assets/js/main.js"></script>


</body>

</html>
