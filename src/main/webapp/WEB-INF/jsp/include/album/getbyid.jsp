<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: mrmrmr
  Date: 2/28/2019
  Time: 6:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="col-md-12">
    <div class="card">
        <div class="card-header">
            <strong class="card-title">Data Table</strong>
        </div>
        <div class="card-body">
            <table id="bootstrap-data-table-export" class="table table-striped table-bordered">
                <thead>
                <tr>
                    <th>Description</th>
                    <th>Author_id</th>
                    <th>Genre_id</th>
                    <th>Price</th>
                </tr>
                </thead>
                <tbody>

                <tr>
                    <th><c:out value="${album.description}"/></th>
                    <th><c:out value="${album.author_id}"/></th>
                    <th><c:out value="${album.genre_id}"/></th>
                    <th><c:out value="${album.price}"/></th>
                </tr>

                </tbody>
            </table>
        </div>
    </div>
</div>