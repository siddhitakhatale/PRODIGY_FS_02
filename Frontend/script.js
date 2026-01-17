document.getElementById("loginForm").addEventListener("submit",async (e) => {
    e.preventDefault();
    const username=e.target.username.value;
    const password=e.target.password.value;

    try{
        const res=await fetch("http://localhost:8080/auth/login",{
            method:"POST",
            headers:{"Content-Type":"application/json" },
            body:JSON.stringify({username,password})
        });
        if(!res.ok){
            throw new Error("HTTP Status "+res.status);
        }
        const data=await res.json();

        localStorage.setItem("token",data.token);
        localStorage.setItem("role",data.role);
        localStorage.setItem("firstLogin",data.firstLogin);

        if(data.nextAction==="SET_PASSWORD"){
            window.location.href="employeePortal.html";
            return;
        }
        if(data.role==="ADMIN"){
            window.location.href="adminportal.html";
        }
        else{
            window.location.href="employeePortal.html";
        }
    }
    catch(err){
        console.error("login error",err);
        alert("invalid username or password");
    }

});