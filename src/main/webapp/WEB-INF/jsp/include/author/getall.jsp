<%--
  Created by IntelliJ IDEA.
  User: mrmrmr
  Date: 2/28/2019
  Time: 6:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="col-md-12">
    <div class="card">
        <div class="card-header">
            <strong class="card-title">Data Table</strong>
        </div>
        <div class="card-body">
            <table id="bootstrap-data-table-export" class="table table-striped table-bordered">
                <thead>
                <tr>
                    <th>First name</th>
                    <th>Second name</th>
                    <th>Pseudomin</th>
                </tr>
                </thead>
                <tbody>
                    <c:forEach var="author" items="${authorList}">
                        <tr>
                            <th><c:out value="${author.firstName}"/></th>
                            <th><c:out value="${author.secondName}"/></th>
                            <th><c:out value="${author.pseudonim}"/></th>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>