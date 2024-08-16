<!Doctype html>
<html>
    <head>
        <title>LOG IN</title>

    </head>

    <body>

        <h1>Welcome to JCASH</h1>

        <form method="post">
            Mobile number:* <input type="text" name="mob_num">
            Password:* <input type="password" name="pin">

            <br/>

            <input type="submit">
        </form>


        <script>
            var error_message = "${error_message}";

            if(error_message.length > 0)
                alert("${error_message}");
            
        </script>

    </body>

</html>