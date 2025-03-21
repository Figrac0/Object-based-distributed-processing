<%@ page contentType="text/html;charset=UTF-8" %>
<html>
    <head>
        <style>
            body {
                font-family: Arial, sans-serif;
                background: #f4f4f4;
                padding: 40px;
                margin: 0;
            }

            h2 {
                color: #333;
                text-align: center;
            }

            form {
                max-width: 400px;
                margin: 0 auto;
                background: #fff;
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }

            input[type="number"] {
                width: 100%;
                padding: 10px;
                margin: 10px 0;
                box-sizing: border-box;
            }

            button {
                padding: 10px 20px;
                margin: 5px;
                background-color: #007bff;
                border: none;
                color: white;
                font-weight: bold;
                border-radius: 5px;
                cursor: pointer;
            }

            button:hover {
                background-color: #0056b3;
            }

            a {
                display: block;
                margin-top: 20px;
                text-align: center;
                text-decoration: none;
                color: #007bff;
            }

            a:hover {
                text-decoration: underline;
            }
        </style>

        <title>Победа!</title>
    </head>
    <body>
        <h2>Компьютер угадал число! 🎉</h2>
        <a href="index.jsp">Сыграть ещё раз</a>
    </body>
</html>
