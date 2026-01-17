const token=localStorage.getItem("token");
const role=localStorage.getItem("role");
let firstLogin=localStorage.getItem("firstLogin") ==="true";

if(!token || role!=="EMPLOYEE"){
    alert("Access denied");
    window.location.href="login.html";
}

const model=document.getElementById("passwordModel");
const saveBtn=document.getElementById("savePasswordBtn");

if(firstLogin){
    model.style.display="flex";
}

saveBtn.addEventListener("click",async() => {
    const pwd=document.getElementById("newPassword").value;
    const confirm=document.getElementById("confirmPassword").value;

    if(pwd!=confirm){
        alert("password do not match");
        return;
    }
    try{
        const res= await fetch("http://localhost:8080/employee/set-password",{
            method:"POST",
            headers:{
                "Content-Type":"application/json",
                "Authorization":"Bearer "+token
            },
            body:JSON.stringify({password:"pwd"})
        });
        if(res.ok){
            alert("password updated successfully!");
            firstLogin=false;
            localStorage.setItem("firstLogin","false");
            model.style.display="none";
            loadEmployeeData();
        }
        else{
            const err=await res.text();
            alert("ERROR" +err);
        }
    }
    catch(err){
        console.error(err);
        alert("something went wrong");
    }
});
async function loadEmployeeData() {
    try{
        const res=await fetch("http://localhost:8080/employee/me",{
            headers:{"Authorization":"Bearer "+token}
        });
        if(res.ok){
            const employee=await res.json();
            const datadiv=document.getElementById("employeeData");
            datadiv.innerHTML=`
                <p><strong>Name:</strong>${employee.name}</p>
                <p><strong>Email:</strong>${employee.email}</p>
                <p><strong>Phone:</strong>${employee.phone}</p>
                <p><strong>Salary:</strong>${employee.salary}</p>
                <p><strong>Department:</strong>${employee.deparment}</p>
                <p><strong>Address:</strong>${employee.address}</p>
                
            `;
        }
        else{
            alert("failed to load employee data");
        }
    }
    catch(err){
        console.error(err);
        alert("Error fetching data");
    }
}
if(!firstLogin){
    loadEmployeeData();
}