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
                <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT"
            crossorigin="anonymous">

        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />        
        <link href="./css/style.css" rel="stylesheet">
        <link rel="stylesheet" href="./assets/fonts/themify-icons/themify-icons.css">
        <style>
            .carousel-item {
                height: 85vh;
            }

            .description {
                border-radius: 10px;
                background: #ddd;
                color: #343a40;
                backdrop-filter: blur(10px);
            }
            .section__header {
                text-align: center;
                margin-top: 8rem;
                margin-bottom: 5rem;
                font-weight: 700;
            }
            .card {
                box-shadow: rgba(0, 0, 0, 0.1) 0px 10px 15px -3px, rgba(0, 0, 0, 0.05) 0px 4px 6px -2px;
                transition: all 0.3s;
                position: relative;
            }
            .card .plant__img {
                height: 15rem;
            }

            .card:hover {
                transform: scale(1.02);
                background: #b2f2bb;
            }

            .card p {
                margin-bottom: 10px;
            }

            .card .plant__name a {
                text-decoration: underline;
                text-decoration-color: transparent;
                transition: all 0.2s;
            }
            .card .plant__name:hover a{
                text-decoration-color: #0d6efd;
            }

            .card a.btn {
                margin-top: 20px;
                width: 47px;
                white-space: nowrap;
                overflow: hidden;
                display: flex;
                align-items: center;
                gap: 10px;
                word-wrap: none;
                transition: all 0.3s;
            }

            .card a.btn:hover {
                width: 110px;
            }



            .plant__name {
                margin-bottom: 10px;
            }
            .plant__price {
                display: inline-block;
                margin-left: 5px;
                font-size: 18px;
                font-weight: 600;
                color: #e03131;
            }
            .footer {
                height: 100px;
            }
            .page-numbers {
                display: flex;
                gap: 5px;
                justify-content: center;
                list-style: none;
            }
            .form-select {
                width: 150px;
                margin-right: 10px;
            }

            #dateFrom, #dateTo {
                border: 1px solid #ced4da;
                border-radius: 0.2rem;
                padding: 5px;
            }

            #dateFrom:focus,
            #dateTo:focus {
                outline: none;
                border-color: 86b7fe;
                box-shadow: 0 0 0 0.25rem rgb(13 110 253 / 25%);
            }
        </style>
    </head>
    <body>

        <%@ include file="../common/header.jsp" %>

        <div class="container">

            <h2 class="mb-5">My Orders</h2>

            <form action="mainController?action=viewAllOrders" method="POST" class="d-flex flex-lg-row flex-column">
                <select class="form-select form-select-sm mb-lg-0 mb-2" name="filter">
                    <option value="all" ${status == 0 ? 'selected' : ''}>All</option>
                    <option value="processing"  ${status == 1 ? 'selected' : ''}>Processing orders</option>
                    <option value="completed"  ${status == 2 ? 'selected' : ''}>Completed orders</option>
                    <option value="canceled"  ${status == 3 ? 'selected' : ''}>Canceled orders</option>
                </select>

                <div class="mb-lg-0 mb-2">
                    <label for="dateFrom">From</label>
                    <input required="required" class="h-100 me-2" type="date" name="dateFrom" id="dateFrom" value="${dateFrom}" >

                    <label for="dateTo">To</label>
                    <input required="required" class="h-100 me-5" type="date" name="dateTo" id="dateTo" value="${dateTo}" />
                </div>

                <input type="submit" value="Filter" class="btn btn-primary">
            </form>

            <table class="table table-bordered">
                <thead>
                    <tr>
                        <th scope="col">Order ID</th>
                        <th scope="col">Order date</th>
                        <th scope="col">Ship date</th>
                        <th scope="col">Status</th>
                        <th scope="col">View order</th>
                    </tr>
                </thead>
                <tbody>

                    <c:forEach items="${listOrders}" var="order">
                        <tr>
                            <td>${order.id}</td>
                            <td>${order.orderDate}</td>
                            <td>${order.shipDate == null ? 'Not yet' : order.shipDate}</td>
                            <td>
                                <c:choose>
                                    <c:when test="${order.status == 1}">
                                        <span class="badge bg-warning">Processing</span>
                                    </c:when>
                                    <c:when test="${order.status == 2}">
                                        <span class="badge bg-success">Completed</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="badge bg-danger">Canceled</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>
                                <a style="text-decoration: none" href="${pageContext.request.contextPath}/mainController?action=viewOrderDetail&orderId=${order.id}&status=${order.status}">Order detail</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>   
        </div> 

        <%@ include file="../common/footer.jsp" %>

        <script src="<c:url value="/resources/bootstrap/js/bootstrap.min.js"/>"></script> 
        <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
    </body>
</html>

