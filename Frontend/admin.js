const API_URL="http://localhost:8080/admin/employees";
const token=localStorage.getItem("token");
if(!token){
    alert("unauthorized");
    window.location.href="login.html";
}
document.addEventListener("DOMContentLoaded",loadEmployees);
document.getElementById("employeeForm").addEventListener("submit",saveEmployee);
document.getElementById("logoutBtn").addEventListener("click",logout);

if (localStorage.getItem("role") !== "ADMIN") {
    alert("Access denied");
    window.location.href = "login.html";
}
function authHeader(){
    return{
        "Authorization":"Bearer " +token
    };
}
function loadEmployees(){
    fetch(API_URL,{headers:authHeader()})
        .then(res => res.json())
        .then(data => {
            const table=document.getElementById("employeeTable");
            const tbody = document.querySelector("#employeeTable tbody");
            tbody.innerHTML = "";

            data.forEach(emp => {
                tbody.innerHTML+=`
                <tr>
                   <td>${emp.name}</td>
                   <td>${emp.email}</td>
                   <td>${emp.phone}</td>
                   <td>${emp.username}</td>
                   <td>${emp.deparment || "-"}</td>
                   <td>${emp.salary || "-"}</td>
                   <td class="actions">
                       <button class="edit" onclick="editEmployee(${emp.id})">Edit</button>
                       <button class="delete" onclick="deleteEmployee(${emp.id})">Delete</button>
                    </td>
                </tr>
                `;
            });
        });
}
function saveEmployee(e) {
    e.preventDefault();

    const id=document.getElementById("empid").value;

    const employee={
        name:document.getElementById("name").value,
        email:document.getElementById("email").value,
        phone:document.getElementById("phone").value,
        username:document.getElementById("username").value,
        salary:document.getElementById("salary").value,
        deparment:document.getElementById("deparment").value,
        address:document.getElementById("address").value
    };

    const method=id ? "PUT" : "POST";
    const url=id ? `${API_URL}/${id}` :API_URL;
    fetch(url,{
        method,
        headers:{
            ...authHeader(),
            "Content-Type":"application/json"
        },
        body:JSON.stringify(employee)
    })
    .then(res => {
        if(!res.ok) throw new Error("Request failed");
        return res.json();
    })
    .then(() => {
        document.getElementById("employeeForm").reset();
        document.getElementById("empid").value="";
        loadEmployees();
    })
    .catch(err => alert(err.message));
}
function editEmployee(id) {
    fetch(`${API_URL}/${id}`,{headers:authHeader()})
        .then(res => res.json())
        .then(emp => {
            document.getElementById("empid").value=emp.id;
            document.getElementById("name").value=emp.name;
            document.getElementById("email").value=emp.email;
            document.getElementById("phone").value=emp.phone;
            document.getElementById("username").value=emp.username;
            document.getElementById("salary").value=emp.salary;
            document.getElementById("deparment").value=emp.deparment;
            document.getElementById("address").value=emp.address;
        });
}
function deleteEmployee(id){
    if(!confirm("Delete the employee")) return;

    fetch(`${API_URL}/${id}`,{
        method:"DELETE",
        headers:authHeader()
    })
    .then(() => loadEmployees());
}

function logout(){
    localStorage.clear();
    window.location.href="login.html";
}