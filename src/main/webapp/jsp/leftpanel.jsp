<%--
  Created by IntelliJ IDEA.
  User: mrmrmr
  Date: 3/1/2019
  Time: 11:41 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8"  %>

<aside id="left-panel" class="left-panel">
    <nav class="navbar navbar-expand-sm navbar-default">
        <div class="navbar-header">
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#main-menu" aria-controls="main-menu" aria-expanded="false" aria-label="Toggle navigation">
                <i class="fa fa-bars"></i>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/jsp/account.jsp"><img src="${pageContext.request.contextPath}/static/images/logo.png" alt="Logo"></a>

            <a class="navbar-brand hidden" href="${pageContext.request.contextPath}/site"><img src="${pageContext.request.contextPath}/static/images/logo2.png" alt="Logo"></a>
        </div>

        <div id="main-menu" class="main-menu collapse navbar-collapse">
            <ul class="nav navbar-nav">
                <li class="active">
                    <a href="${pageContext.request.contextPath}/jsp/account.jsp"> <i class="menu-icon fa fa-dashboard"></i>Dashboard </a>
                </li>
                <li class="menu-item-has-children dropdown">
                    <h3 class="menu-title">UI elements</h3><!-- /.menu-title -->
                </li>
                <li class="menu-item-has-children dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true"> <i class="menu-icon fa fa-laptop"></i>Components</a>
                    <ul class="sub-menu children active dropdown-menu">
                        <li><i class="fa fa-puzzle-piece"></i><a href="${pageContext.request.contextPath}/site/ui-buttons.html">Buttons</a></li>
                        <li><i class="fa fa-id-badge"></i><a href="${pageContext.request.contextPath}/site/ui-badges.html">Badges</a></li>
                        <li><i class="fa fa-bars"></i><a href="${pageContext.request.contextPath}/site/ui-tabs.html">Tabs</a></li>
                        <li><i class="fa fa-share-square-o"></i><a href="${pageContext.request.contextPath}/site/ui-social-buttons.html">Social Buttons</a></li>
                        <li><i class="fa fa-id-card-o"></i><a href="${pageContext.request.contextPath}/site/ui-cards.html">Cards</a></li>
                        <li><i class="fa fa-exclamation-triangle"></i><a href="${pageContext.request.contextPath}/site/ui-alerts.html">Alerts</a></li>
                        <li><i class="fa fa-spinner"></i><a href="${pageContext.request.contextPath}/site/ui-progressbar.html">Progress Bars</a></li>
                        <li><i class="fa fa-fire"></i><a href="${pageContext.request.contextPath}/site/ui-modals.html">Modals</a></li>
                        <li><i class="fa fa-book"></i><a href="${pageContext.request.contextPath}/site/ui-switches.html">Switches</a></li>
                        <li><i class="fa fa-th"></i><a href="${pageContext.request.contextPath}/site/ui-grids.html">Grids</a></li>
                        <li><i class="fa fa-file-word-o"></i><a href="${pageContext.request.contextPath}/site/ui-typgraphy.html">Typography</a></li>
                    </ul>
                </li>

                <li class="menu-item-has-children dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-laptop"></i>CRUD operations</a>
                    <ul class="sub-menu children dropdown-menu">
                        <li><i class="fa fa-puzzle-piece"></i><a href="${pageContext.request.contextPath}/jsp/crud/user.jsp">UserDAO CRUD</a></li>
                        <li><i class="fa fa-puzzle-piece"></i><a href="${pageContext.request.contextPath}/jsp/crud/author.jsp">AuthorDAO CRUD</a></li>
                        <li><i class="fa fa-puzzle-piece"></i><a href="${pageContext.request.contextPath}/jsp/crud/genre.jsp">GenreDAO CRUD</a></li>
                        <li><i class="fa fa-puzzle-piece"></i><a href="${pageContext.request.contextPath}/jsp/crud/role.jsp">RoleDAO CRUD</a></li>
                        <li><i class="fa fa-puzzle-piece"></i><a href="${pageContext.request.contextPath}/jsp/crud/album.jsp">AlbumDAO CRUD</a></li>
                        <li><i class="fa fa-puzzle-piece"></i><a href="${pageContext.request.contextPath}/jsp/crud/albumfeedback.jsp">AlbumFeedbackDAO CRUD</a></li>
                        <li><i class="fa fa-puzzle-piece"></i><a href="${pageContext.request.contextPath}/jsp/crud/bonus.jsp">BonusDAO CRUD</a></li>
                        <li><i class="fa fa-puzzle-piece"></i><a href="${pageContext.request.contextPath}/jsp/crud/composition.jsp">CompositionDAO CRUD</a></li>
                        <li><i class="fa fa-puzzle-piece"></i><a href="${pageContext.request.contextPath}/jsp/crud/compositionfeedback.jsp">CompositionFeedbackDAO CRUD</a></li>
                        <li><i class="fa fa-puzzle-piece"></i><a href="${pageContext.request.contextPath}/jsp/crud/musicselection.jsp">MusicSelectionDAO CRUD</a></li>
                        <li><i class="fa fa-puzzle-piece"></i><a href="${pageContext.request.contextPath}/jsp/crud/musicselectionfeedbcak.jsp">MusicSelectionFeedbackDAO CRUD</a></li>
                        <li><i class="fa fa-puzzle-piece"></i><a href="${pageContext.request.contextPath}/jsp/crud/useralbum.jsp">UserAlbumDAO CRUD</a></li>
                        <li><i class="fa fa-puzzle-piece"></i><a href="${pageContext.request.contextPath}/jsp/crud/userbonus.jsp">UserBonusDAO CRUD</a></li>
                        <li><i class="fa fa-puzzle-piece"></i><a href="${pageContext.request.contextPath}/jsp/crud/usercomposition.jsp">UserCompositionDAO CRUD</a></li>
                        <li><i class="fa fa-puzzle-piece"></i><a href="${pageContext.request.contextPath}/jsp/crud/usermusicselection.jsp">UserMusicSelectionDAO CRUD</a></li>
                    </ul>
                </li>

                <li class="menu-item-has-children dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-table"></i>Tables</a>
                    <ul class="sub-menu children dropdown-menu">
                        <li><i class="fa fa-table"></i><a href="${pageContext.request.contextPath}/site/tables-basic.html">Basic Table</a></li>
                        <li><i class="fa fa-table"></i><a href="${pageContext.request.contextPath}/site/tables-data.html">Data Table</a></li>
                    </ul>
                </li>

                <li class="menu-item-has-children dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-th"></i>Forms</a>
                    <ul class="sub-menu children dropdown-menu">
                        <li><i class="menu-icon fa fa-th"></i><a href="${pageContext.request.contextPath}/site/forms-basic.html">Basic Form</a></li>
                        <li><i class="menu-icon fa fa-th"></i><a href="${pageContext.request.contextPath}/site/forms-advanced.html">Advanced Form</a></li>
                    </ul>
                </li>

                <li class="menu-item-has-children dropdown">
                    <h3 class="menu-title">Icons</h3><!-- /.menu-title -->
                </li>

                <li class="menu-item-has-children dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-tasks"></i>Icons</a>
                    <ul class="sub-menu children dropdown-menu">
                        <li><i class="menu-icon fa fa-fort-awesome"></i><a href="${pageContext.request.contextPath}/site/font-fontawesome.html">Font Awesome</a></li>
                        <li><i class="menu-icon ti-themify-logo"></i><a href="${pageContext.request.contextPath}/site/font-themify.html">Themefy Icons</a></li>
                    </ul>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/site/widgets.html"> <i class="menu-icon ti-email"></i>Widgets </a>
                </li>
                <li class="menu-item-has-children dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-bar-chart"></i>Charts</a>
                    <ul class="sub-menu children dropdown-menu">
                        <li><i class="menu-icon fa fa-line-chart"></i><a href="${pageContext.request.contextPath}/site/charts-chartjs.html">Chart JS</a></li>
                        <li><i class="menu-icon fa fa-area-chart"></i><a href="${pageContext.request.contextPath}/site/charts-flot.html">Flot Chart</a></li>
                        <li><i class="menu-icon fa fa-pie-chart"></i><a href="${pageContext.request.contextPath}/site/charts-peity.html">Peity Chart</a></li>
                    </ul>
                </li>

                <li class="menu-item-has-children dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-area-chart"></i>Maps</a>
                    <ul class="sub-menu children dropdown-menu">
                        <li><i class="menu-icon fa fa-map-o"></i><a href="${pageContext.request.contextPath}/site/maps-gmap.html">Google Maps</a></li>
                        <li><i class="menu-icon fa fa-street-view"></i><a href="${pageContext.request.contextPath}/site/maps-vector.html">Vector Maps</a></li>
                    </ul>
                </li>
                <li class="menu-item-has-children dropdown">
                    <h3 class="menu-title">Extras</h3><!-- /.menu-title -->
                </li>
                <li class="menu-item-has-children dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> <i class="menu-icon fa fa-glass"></i>Pages</a>
                    <ul class="sub-menu children dropdown-menu">
                        <li><i class="menu-icon fa fa-sign-in"></i><a href="${pageContext.request.contextPath}/site/page-login.html">Login</a></li>
                        <li><i class="menu-icon fa fa-sign-in"></i><a href="${pageContext.request.contextPath}/site/page-register.html">Register</a></li>
                        <li><i class="menu-icon fa fa-paper-plane"></i><a href="${pageContext.request.contextPath}/site/pages-forget.html">Forget Pass</a></li>
                    </ul>
                </li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </nav>
</aside><!-- /#left-panel -->

<script src="${pageContext.request.contextPath}/site/vendors/jquery/dist/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/site/vendors/popper.js/dist/umd/popper.min.js"></script>

<script src="${pageContext.request.contextPath}/site/vendors/jquery-validation/dist/jquery.validate.min.js"></script>
<script src="${pageContext.request.contextPath}/site/vendors/jquery-validation-unobtrusive/dist/jquery.validate.unobtrusive.min.js"></script>

<script src="${pageContext.request.contextPath}/site/vendors/bootstrap/dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/site/assets/js/main.js"></script>