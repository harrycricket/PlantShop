<%@page import="se160205.dao.PlantDAO"%>
<%@page import="se160205.dto.Plant"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart Page</title> 
        <link rel="stylesheet" href="<c:url value="/resources/bootstrap/css/bootstrap.min.css"/>"/>
        <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>

        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Poppins:wght@100;200;300;400;500;600;700;800;900&display=swap" rel="stylesheet">

        <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
        <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT"
            crossorigin="anonymous">
        <script
            src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
            integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3"
        crossorigin="anonymous"></script>
        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js"
            integrity="sha384-7VPbUDkoPSGFnVtYi0QogXtr74QeVeeIs99Qfg5YCF+TidwNdjvaKZX19NZ/e6oz"
        crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />        
        <link href="./css/style.css" rel="stylesheet">
        <link rel="stylesheet" href="./assets/fonts/themify-icons/themify-icons.css">
    </head>
    <body>

        <%@ include file="../common/header.jsp" %>
        <div class="container">
            <h1 class="mt-3 mb-5">Cart Items</h1>
            <h4 class="text-center text-success">${announcement}</h4>
            <h4 class="text-center text-danger ">${error}</h4>
            <div class="list__items">
                <c:choose>
                    <c:when test="${cart != null && !cart.isEmpty()}">
                        <c:forEach items="${cart}" var="entry">
                            <c:set var="plant" scope="page" value="${entry.key}" />
                            <form action="mainController" method="POST" 
                                  class="item border border-1 border-top-0 border-start-0 border-end-0 d-flex align-items-center justify-content-between px-5 py-3">

                                <input type="hidden" name="id" value="${plant.id}">

                                <div class="item__image">
                                    <img class="img-responsive"  style="width: 150px; height: 110px" src="<c:url value="/resources/${plant.imgPath}"/>" alt="alt"/>
                                </div>
                                <div class="item__info">
                                    <p class="item__info--id">ID: 
                                        <a href="${pageContext.request.contextPath}/mainController?action=viewPlant&pId=${plant.id}">${plant.id}</a>
                                    </p>
                                    <b class="item__info--name">${plant.name}</b>
                                </div>

                                <div class="item__quantity">
                                    <input type="number" value="${entry.value}" min="0" name="quantity"/>
                                </div>

                                <div class="item__action d-flex">
                                    <button onclick="if (!confirm('Do you want to update this item?')) {
                                                return false;
                                            }
                                            " class="btn btn-success me-2" type="submit" value="btn-update" name="action">Update</button>
                                    <button onclick="if (!confirm('Do you want to delete this item?')) {
                                                return false;
                                            }" class="btn btn-danger" type="submit" value="deleteCartItem"  name="action" >&times;</button>
                                </div>
                            </form>
                        </c:forEach>
                        <div class="total-price py-3 pe-5">
                            <span>Total: ${totalPrice} VND</span>
                            <a onclick="
                                    if (!confirm('Do you want to order?')) {
                                        return false;
                                    }
                               " href="mainController?action=checkout&total=${totalPrice}" class="btn btn-success">Save order</a>
                            <a href="mainController?action=viewAllOrders" class="btn btn-success">View all orders</a>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <h5 class="text-center text-danger">${warning}</h5>
                    </c:otherwise>
                </c:choose>

            </div>
        </div> 
        <%@ include file="../common/footer.jsp" %>

    </body>
</html>