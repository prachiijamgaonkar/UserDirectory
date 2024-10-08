<%@page import="com.edu.myapp.dto.Role"%>
<%@page import="java.util.List"%>
<%@page import="com.edu.myapp.dto.UserDTO"%>
<%@page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User List</title>
    <style>
        body {
            font-family: 'Arial', sans-serif;
            background-color: #f0f4f8;
            color: #333;
            margin: 0;
            padding: 20px;
            display: flex;
            justify-content: center; 
            align-items: center; 
            height: 100vh; 
            overflow-x: hidden; 
        }

        .container {
            background: #ffffff;
            border-radius: 12px;
            box-shadow: 0 4px 20px rgba(0, 0, 0, 0.1);
            padding: 30px;
            margin: auto;
            width: 100%; 
            max-width: 900px; 
            transition: all 0.3s ease;
        }

        .container:hover {
            box-shadow: 0 8px 40px rgba(0, 0, 0, 0.2);
        }

        table {
            width: 100%; /* Full width */
            border-collapse: collapse;
            margin-top: 20px;
            border-radius: 12px;
            overflow: hidden; 
        }

        th, td {
            padding: 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
            word-wrap: break-word; 
            max-width: 150px; 
        }

        th {
            background-color: #007BFF;
            color: white;
            text-transform: uppercase;
            letter-spacing: 0.05em;
        }

        tr:hover {
            background-color: #f1f8ff; 
            cursor: pointer;
        }

        a {
            color: #007BFF;
            text-decoration: none;
            transition: color 0.3s ease;
        }

        a:hover {
            color: #0056b3; 
            text-decoration: underline;
        }

        .action-button {
            color: #ff4d4d; 
            font-weight: bold;
            transition: color 0.3s ease;
            margin-right: 10px; 
        }

        .action-button:hover {
            color: #c0392b; 
        }

        .no-users {
            text-align: center;
        }

        /* Responsive Design */
        @media (max-width: 768px) {
            body {
                padding: 10px;
            }

            .container {
                padding: 15px;
            }

            th, td {
                padding: 10px;
                max-width: 100px; 
            }

            .action-button {
                font-size: 0.9em; 
            }
        }

        
        .home-link {
            text-align: center;
            margin-top: 20px; 
        }
    </style>
</head>
<body>
    <%
        @SuppressWarnings("unchecked")
        List<UserDTO> users = (List<UserDTO>) request.getAttribute("users");
        if (users == null || users.isEmpty()) {
    %>
        <div class="container no-users">
            <h3>No users found.</h3>
            <a href="home">HOME</a>
        </div>
    <%
        } else {
    %>
        <div class="container">
            <table>
                <tr>
                    <th>ID</th>
                    <th>FIRSTNAME</th>
                    <th>LASTNAME</th>
                    <th>MOBILE</th>
                    <th>EMAIL</th>
                    <th>ADDRESS</th>
                    <th>ROLE</th>
                </tr>
                <%
                for (UserDTO user : users) {
                    String userRole = user.getRole().toString(); 
                %>
                    <tr style="<%= userRole.equals(Role.USER.toString()) ? "" : "color: red;" %>">
                        <td><%= user.getId() %></td>
                        <td><%= user.getFirstName() %></td>
                        <td><%= user.getLastName() %></td>
                        <td><%= user.getMobile() %></td>
                        <td><%= user.getEmail() %></td>
                        <td><%= user.getAddress() %></td>
                        <td><%= userRole %></td>
                    </tr>
                <%
                }
                %>
            </table>
            <div class="home-link">
                <a href="home">HOME</a>
            </div>
        </div>
    <%
        }
    %>
</body>
</html>
