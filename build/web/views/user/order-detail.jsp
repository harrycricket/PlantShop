<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title> 
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
            <h2 class="mt-5 mb-3">Order ID: ${orderId}</h2>
            <h5>Status:
                <c:choose>
                    <c:when test="${status == 1}">
                        <span class="badge bg-warning">Processing</span>
                    </c:when>
                    <c:when test="${status == 2}">
                        <span class="badge bg-success">Completed</span>
                    </c:when>
                    <c:otherwise>
                        <span class="badge bg-danger">Canceled</span>
                    </c:otherwise>
                </c:choose>
            </h5>

            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th scope="col">Plant ID</th>
                        <th scope="col">Plant name</th>
                        <th class="w-25" scope="col">Image</th>
                        <th scope="col">Price</th>
                        <th scope="col">Quantity</th>
                    </tr>
                </thead>
                <tbody>

                    <c:forEach items="${listOrderDetails}" var="detail">
                        <tr>
                            <td>${detail.plantId}</td>
                            <td>${detail.plantName}</td>
                            <td>
                                <div class="plant__img">
                                    <img style="width: 200px" src="<c:url value="/resources/${detail.imgPath}"></c:url>" alt=""/>
                                    </div>
                                </td>
                                <td>
                                ${detail.price}VND
                            </td>
                            <td>
                                ${detail.quantity}
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>

            </table>
            <div class="ms-auto d-flex justify-content-end">
                <c:choose>
                    <c:when test="${status == 1}">
                        <a href="${pageContext.request.contextPath}/mainController?action=changeOrderStatus&orderId=${orderId}&status=3" class="btn btn-danger">Cancel order</a>
                    </c:when>
                    <c:when test="${status == 3}">
                        <a href="${pageContext.request.contextPath}/mainController?action=changeOrderStatus&orderId=${orderId}&status=1" class="btn btn-success">Order again</a>
                    </c:when>
                </c:choose>
                <a href="${pageContext.request.contextPath}/mainController?action=viewAllOrders" class="btn btn-success ms-2">View all orders</a>
            </div>
        </div> 

        <%@ include file="../common/footer.jsp" %>
        <script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js"/>"></script> 
    </body>
</html>

