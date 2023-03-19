<%-- 
    Document   : header
    Created on : Feb 25, 2023, 10:35:27 AM
    Author     : Huynh Van Phuot
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/WEB-INF/tlds/welcome_tag.tld" prefix="customTag"%>

<!DOCTYPE html>

<html>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light px-5" style="background-color: rgb(80 244 24 / 30%) !important">
            <div class="container-fluid">

                <a class="navbar-brand" href="${pageContext.request.contextPath}/">
                    <img style="width: 70px; height: 70px;" class="img-responsive" src="<c:url value="/resources/images/logo.jpg"/>" alt="alt"/>
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>

                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <c:choose>
                        <c:when test="${loginedUser != null && loginedUser.role == 1}">
                            <ul class="navbar-nav mb-2 mb-lg-0 ms-auto me-5">
                                <li class="nav-item">
                                    <a class="nav-link" href="${pageContext.request.contextPath}/admin?action=viewAllUsers">Accounts</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="${pageContext.request.contextPath}/admin?action=viewAllOrders">Orders</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="${pageContext.request.contextPath}/admin?action=viewAllPlants">Plants</a>
                                </li>
                                <li class="nav-item">
                                    <a class="nav-link" href="${pageContext.request.contextPath}/admin?action=viewAllCategories">Categories</a>
                                </li>
                            </ul>

                            <customTag:welcome name="Administrator"  />

                        </c:when>

                        <c:otherwise>
                            <ul class="navbar-nav mb-2 mb-lg-0 ms-auto me-5">
                                <li class="nav-item">
                                    <a class="nav-link active" aria-current="page" href="${pageContext.request.contextPath}/">Home</a>
                                </li>

                                <li class="nav-item">
                                    <a class="nav-link" href="${pageContext.request.contextPath}/mainController?action=viewCart">View cart</a>
                                </li>
                                <c:choose>
                                    <c:when test="${loginedUser != null}">                  
                                        <li class="nav-item">
                                            <a class="nav-link" href="${pageContext.request.contextPath}/mainController?action=changeProfile">Change Profile</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="${pageContext.request.contextPath}/mainController?action=viewAllOrders">My Orders</a>
                                        </li>
                                    </c:when>
                                    <c:otherwise>
                                        <li class="nav-item">
                                            <a class="nav-link" href="${pageContext.request.contextPath}/mainController?action=register">Register</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" href="${pageContext.request.contextPath}/mainController?action=login">Login</a>
                                        </li>
                                    </c:otherwise>
                                </c:choose>
                            </ul>
                            <form class="d-flex me-5" action="mainController" method="POST">
                                <input type="hidden" name="action" value="search">
                                <div class="input-group">
                                    <input class="form-control me-2" type="text" name="textSearch" value="${textSearch}" placeholder="Type to search">
                                    <c:if test="${searchError != null}">
                                        <div class="search__error">
                                            Enter plant or category name to search
                                        </div>
                                    </c:if>

                                    <select class="form-select me-2" name="searchBy">
                                        <option value="byname" ${searchBy == 'byname' ? 'selected' : ''}>By Name</option>
                                        <option value="bycategory" ${searchBy == 'bycategory' ? 'selected' : ''}>By Category</option>
                                    </select>
                                    <button class="btn btn-outline-success" type="submit">Search</button>
                                </div>
                            </form>

                            <c:if test="${loginedUser != null}">
                                <customTag:welcome name="${loginedUser.fullname}"/>
                            </c:if>

                        </c:otherwise>
                    </c:choose>

                </div>
            </div>
        </nav>
    </body>
</html>