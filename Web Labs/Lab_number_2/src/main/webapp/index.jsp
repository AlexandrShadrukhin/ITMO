<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="ru-Ru">
<head>
    <meta name="viewport" content="initial-scale=1.0"/>
    <meta http-equiv="Content-Type" content="text/html" charset="UTF-8"/>
    <link rel="stylesheet" href="styles/mainPage.css">
    <title>web2</title>
</head>
<body>
<div id="container" class="margin">
    <div id="header" class="blured-container round-container margin ">
        <p>Web_Lab_2</p>
        <p>Шадрухин Александр P3225</p>
        <p>Вариант 861207</p>
    </div>
    <div id="main" class="margin">
        <div id="choose" class="blured-container round-container margin">
            <p>Введите значения:</p>
            <p>Каждое поле должно быть заполнено</p>
            <form action="${pageContext.request.contextPath}/controller" method="post">
                <div id="x-select-container" class="select-container margin">
                    <label for="x-select-input">Выберите X:</label>
                    <div class="select-input-container">
                        <div id="x-select-input" class="select-radio-container">
                            <input type="button" name="x-select" value="-2"/>
                            <input type="button" name="x-select" value="-1.5"/>
                            <input type="button" name="x-select" value="-1"/>
                            <input type="button" name="x-select" value="-0.5"/>
                            <input type="button" name="x-select" value="0"/>
                            <input type="button" name="x-select" value="0.5"/>
                            <input type="button" name="x-select" value="1"/>
                            <input type="button" name="x-select" value="1.5"/>
                            <input type="button" name="x-select" value="2"/>
                        </div>
                        <span id="x-select-error" class="error" aria-live="polite"></span>
                    </div>
                </div>
                <div id="y-select-container" class="select-container margin">
                    <label for="y-select">Введите Y:</label>
                    <div id="y-select-input" class="select-input-container">
                        <input type="text" name="y-select" id="y-select" class="input-select"
                               placeholder="В диапазоне [-5; 3]" required/>
                        <span class="error" aria-live="polite"></span>
                    </div>
                </div>
                <div id="r-select-container" class="select-container margin">
                    <label for="r-select-input">Выберите R:</label>
                    <div class="select-input-container">
                        <div id="r-select-input" class="select-radio-container">
                            <input type="radio" id="r-select1" name="r-select" value="1" required/>
                            <label for="r-select1">1</label>
                            <input type="radio" id="r-select2" name="r-select" value="2"/>
                            <label for="r-select2">2</label>
                            <input type="radio" id="r-select3" name="r-select" value="3"/>
                            <label for="r-select3">3</label>
                            <input type="radio" id="r-select4" name="r-select" value="4"/>
                            <label for="r-select4">4</label>
                            <input type="radio" id="r-select5" name="r-select" value="5"/>
                            <label for="r-select5">5</label>
                        </div>
                        <span id="r-select-error" class="error" aria-live="polite"></span>
                    </div>
                </div>
                <div id="submit-container" class="margin">
                    <input type="submit" id="submit-button" value="Подтвердить">
                </div>
            </form>
            <form method="POST" action="${pageContext.request.contextPath}/controller" id="hidden-form">
                <input type="hidden" id="hidden-x" name="hidden-x"/>
                <input type="hidden" id="hidden-y" name="hidden-y"/>
                <input type="hidden" id="hidden-r" name="hidden-r"/>
            </form>
        </div>
        <div id="graph-container" class="blured-container round-container margin">
            <canvas id="graph" width="500px" height="500px"></canvas>
        </div>
        <div id="result-table-container" class="blured-container margin">
            <table id="result-table">
                <thead>
                <tr>
                    <th>X</th>
                    <th>Y</th>
                    <th>R</th>
                    <th>Результат</th>
                    <th>Текущее время</th>
                    <th>Выполнено за, нс</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="result" items="${resultList}">
                    <tr>
                        <td>${result.x}</td>
                        <td>${result.y}</td>
                        <td>${result.r}</td>
                        <td>${result.result ? 'Попадание' : 'Промах'}</td>
                        <td>${result.calculatedAt}</td>
                        <td>${result.calculationTime}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
<script src="scripts/fix-placeholder.js"></script>
<script src="scripts/get-intersection.js"></script>
<script src="scripts/graph.js"></script>
<script src="scripts/validation.js"></script>
<script type="text/javascript">
    var resultList = [];

    <c:forEach var="result" items="${resultList}">
    var item = {
        x: ${result.x},
        y: ${result.y},
        r: ${result.r},
        result: ${result.result},
        calculationTime: ${result.calculationTime},
        calculatedAt: "${result.calculatedAt}"
    };
    resultList.push(item);
    drawPoint(${result.x}, ${result.y}, ${result.result});
    </c:forEach>
</script>
</body>
</html>
