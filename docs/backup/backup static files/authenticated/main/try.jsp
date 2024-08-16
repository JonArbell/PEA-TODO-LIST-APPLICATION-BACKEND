<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Try</title>
</head>
<body>
    <form:form modelAttribute="list" method="post" action="/pea/create/list" id="create-acc-form">
    
    <label for="text">Enter list name : </label>
    <form:input type="text" placeholder="list name" path="listName"/>
    <br/>
    <input type="submit" value="Submit"/>
    </form:form>
</body>
</html>