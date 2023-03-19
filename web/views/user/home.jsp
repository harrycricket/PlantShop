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

        </style>

    </head>
    <body>

        <%@ include file="../common/header.jsp" %>

        <!-- plants -->       

        <div class="container">
            <h2 class="section__header">ALL PLANTS</h2>
            <div class="row">
                <c:forEach items="${listPlants}" var="plant">
                    <div class="col-lg-3 col-6 mb-5">
                        <div class="card">
                            <img class="img-responsive plant__img card-img-top" src="<c:url value="./resources/${plant.imgPath}"/>">
                            <div class="card-body">
                                <h5 class="plant__name">
                                    <a href="${pageContext.request.contextPath}/mainController?action=viewPlant&pId=${plant.id}">${plant.name}</a>
                                </h5>
                                <p class="plant__id">Plant ID: ${plant.id}</p>
                                <p >Price:<span class="plant__price">${plant.price}VND</span></p>
                                <p class="plant__category">Category: ${plant.cateName} </p>
                                <button class="buy-button"><a class="add-item" href="mainController?action=addToCart&pId=${plant.id}">Add to card</a></button> 
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
        <div class="py-5 text-center">
            <ul class="d-flex page-numbers  justify-content-center">
                <c:if test="${currentPage != 1}">
                    <li><a class="btn btn-primary" href="mainController?action=home&page=${currentPage - 1}">Previous</a></li>
                    </c:if>

                <c:forEach begin="1" end="${numOfPages}" var="i">
                    <c:choose>
                        <c:when test="${currentPage == i}">
                            <li class="btn btn-success">${i}</li>
                            </c:when>
                            <c:otherwise>
                            <li><a  class="btn btn-primary" href="mainController?action=home&page=${i}">${i}</a></li>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

                <c:if test="${currentPage < numOfPages}">
                    <li ><a class="btn btn-primary" href="mainController?action=home&page=${currentPage +  1}">Next</a></li>
                    </c:if>

            </ul>
        </div>
        <%@ include file="../common/footer.jsp" %>

        <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
        <script
            src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
            integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3"
        crossorigin="anonymous"></script>
        <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js"
            integrity="sha384-7VPbUDkoPSGFnVtYi0QogXtr74QeVeeIs99Qfg5YCF+TidwNdjvaKZX19NZ/e6oz"
        crossorigin="anonymous"></script>
    </body>
</html>
