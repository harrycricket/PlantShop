<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Plant</title>
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
            .form-add-plant {
                max-width: 500px;
                width: 90%;
            }
            
        </style>
    </head>

    <body>
        <%@include file="../common/header.jsp" %>

        <div class="container">
            <form class="form px-4 form-add-plant" action="${pageContext.request.contextPath}/savePlant" method="POST">
                <input type="hidden" name="action" value="${action}">
                <input type="hidden" name="pId" value="${plant.id != null ? plant.id : ''}">
                
                <h2 class="text-start mb-3 mt-5">${action == 'update' ? 'Update Plant' : 'Add new plant'}</h2>
                <div class="mb-3">
                    <div class="input-group">
                        <input type="text" class="form-control"  placeholder="Enter plant name" name="name" value="${plant.name}" >

                        <div class="invalid-feedback">
                            ${nameError}
                        </div>
                    </div>
                </div>

                <div class="mb-3">
                    <div class="input-group">
                        <input type="number" class="form-control"  placeholder="Enter plant price" name="price" value="${plant.price}"/>


                        <div class="invalid-feedback">
                            ${priceError}
                        </div>

                    </div>
                </div>
                <div class="mb-3">
                    <div class="input-group">

                        <input type="text" class="form-control"  placeholder="Enter image path" name="imgPath" value="${plant.imgPath}" >


                        <div class="invalid-feedback">
                            ${imgPathError}
                        </div>
                    </div>
                </div>

                <div class="mb-3">
                    <div class="input-group">
                        <textarea id="id" class="form-control" name="Enter description of plant" rows="5" cols="10" >${plant.description}</textarea>
                    </div>
                </div>

                <div class="form-group">
                    <label for="selectCate" class="mb-2">Category</label>
                    <select class="form-control" id="selectCate" name="cateId">
                        <option value="0">Choose a category</option>
                        <c:forEach items="${categories}" var="cate">
                            <option value="${cate.id}" ${plant.cateId == cate.id ? 'selected' : ''}>${cate.name}</option>
                        </c:forEach>
                    </select>
                    <div class="invalid-feedback">
                        ${cateError}
                    </div>
                </div>

                <div class="text-start mt-3">
                    <input class="btn btn-primary ms-auto" type="submit" value="${action == 'update' ? 'Update' : 'Add'}" />
                </div>
            </form>
        </div>

        <%@include file="../common/footer.jsp" %>
        
        <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
    </body>
</html>

