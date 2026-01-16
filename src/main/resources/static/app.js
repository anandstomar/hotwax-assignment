const API_URL = "http://localhost:8080";

// 1. Handle Login
async function login() {
    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;
    const errorMsg = document.getElementById("login-error");

    try {
        const response = await fetch(`${API_URL}/users/authenticate`, {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ name: username, password: password })
        });

        if (response.ok) {
            // The response text is the JWT Token
            const token = await response.text();
            localStorage.setItem("jwt_token", token); // Save token to browser
            showDashboard();
        } else {
            errorMsg.innerText = "Invalid Username or Password";
        }
    } catch (error) {
        errorMsg.innerText = "Connection Error";
        console.error(error);
    }
}

// 2. Fetch and Display Orders
async function fetchOrders() {
    const token = localStorage.getItem("jwt_token");

    try {
        const response = await fetch(`${API_URL}/orders`, {
            method: "GET",
            headers: {
                "Authorization": "Bearer " + token, // Send Token in Header
                "Content-Type": "application/json"
            }
        });

        if (response.ok) {
            const orders = await response.json();
            renderOrders(orders);
        } else if (response.status === 403) {
            alert("Session expired. Please login again.");
            logout();
        }
    } catch (error) {
        console.error("Error fetching orders:", error);
    }
}

function renderOrders(orders) {
    const tbody = document.getElementById("orders-body");
    tbody.innerHTML = ""; 

    orders.forEach(order => {
        const row = `
            <tr>
                <td><span style="font-weight:500; color:#2563eb">#${order.orderId}</span></td>
                <td>${order.orderDate}</td>
                <td>
                    <div style="font-weight:500">${order.customer ? order.customer.firstName : 'Unknown'}</div>
                </td>
                <td>${order.orderItems.length} Items</td>
                <td class="text-right">
                    <button class="btn btn-danger" onclick="deleteOrder(${order.orderId})">
                        Delete
                    </button>
                </td>
            </tr>
        `;
        tbody.innerHTML += row;
    });
}

// 4. Delete Order
async function deleteOrder(id) {
    if(!confirm("Are you sure?")) return;
    
    const token = localStorage.getItem("jwt_token");
    await fetch(`${API_URL}/orders/${id}`, {
        method: "DELETE",
        headers: { "Authorization": "Bearer " + token }
    });
    fetchOrders(); // Refresh list
}

// 5. UI Helpers
function showDashboard() {
    document.getElementById("login-section").classList.add("hidden");
    document.getElementById("dashboard-section").classList.remove("hidden");
    fetchOrders(); // Load data immediately
}

function logout() {
    localStorage.removeItem("jwt_token");
    location.reload(); // Reload page to reset
}

// Check if already logged in on page load
window.onload = () => {
    if (localStorage.getItem("jwt_token")) {
        showDashboard();
    }
};