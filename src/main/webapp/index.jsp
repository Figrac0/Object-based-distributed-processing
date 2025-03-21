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

        <title>Начать игру</title>
    </head>
    <body>
        <h2>Загадайте число в диапазоне</h2>
        <form action="game" method="post">
            Минимум: <input type="number" name="min" required /><br />
            Максимум: <input type="number" name="max" required /><br />
            <input type="hidden" name="action" value="start" />
            <button type="submit">Начать игру</button>
        </form>
    </body>
</html>
