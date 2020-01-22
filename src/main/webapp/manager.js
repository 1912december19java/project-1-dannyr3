'use strict';

let body = document.getElementsByTagName("body");
let tb1 = document.getElementById("tb-1");
let tb2 = document.getElementById("tb-2");
let tb3 = document.getElementById("tb-3");
let uname = document.getElementById("username");
let passw = document.getElementById("password");
let fname = document.getElementById("firstName");
let lname = document.getElementById("lastName");
let esubmit = document.getElementById("e-submit");

const queryString = window.location.search;

const urlParams = new URLSearchParams(queryString);

const i = urlParams.get('Id');
console.log(i);
console.log('1');

esubmit.addEventListener('click', (e) => {

    e.preventDefault();
    updateInfo();

});

async function setApprovedReimbursement(reinbursements) {

    let myUri = "http://localhost:8080/project-1-dannyr3/api/reinsbursement";

    reinbursements['manager_Id'] = i;
    reinbursements['status'] = "Approved";
    reinbursements['approver_comments'] = "Approved";



    let response = await fetch(myUri, { method: 'POST', body: JSON.stringify(reinbursements) });

    getAllReinbursements();
}

async function setDeniedReimbursement(reinbursements) {

    let myUri = "http://localhost:8080/project-1-dannyr3/api/reinsbursement";

    reinbursements['manager_Id'] = i;
    reinbursements['status'] = "Denied";
    reinbursements['approver_comments'] = "Overbudget";

    let response = await fetch(myUri, { method: 'POST', body: JSON.stringify(reinbursements) });

    getAllReinbursements();
}


async function updateInfo() {

    let myUri = "http://localhost:8080/project-1-dannyr3/api/employees";

    let employee = {};
    employee.id = i;
    employee.username = uname.value;
    employee.password = passw.value;
    employee.firstName = fname.value;
    employee.lastName = lname.value;

    let response = await fetch(myUri, { method: 'POST', body: JSON.stringify(employee) });

    uname.placeholder = uname.value;
    passw.placeholder = passw.value;
    fname.placeholder = fname.value;
    lname.placeholder = lname.value;
}

async function getReinbursementsEmployeeId(i) {
    while (tb1.hasChildNodes()) {
        tb1.removeChild(tb1.firstChild);
    }

    while (tb2.hasChildNodes()) {
        tb2.removeChild(tb2.firstChild);
    }

    console.log("called");
    console.log(`${i}`);
    await fetch(`http://localhost:8080/project-1-dannyr3/api/reinbursements/${i}`)
        .then((Response) => {
            return Response.json()
        })
        .then((reinbursements) => {
            for (let y = 0; y < reinbursements.length; y++) {
                //let temp = reinbursements[y];
                // Rid Eid D&T Type Status Price RC I
                //Rid Eid Mid D&T Type Status Price RC AC I
                let tr = document.createElement('tr');
                console.log('we in there');

                if (reinbursements[y]['status'] === 'Pending') {
                    for (let key in reinbursements[y]) {
                        if (['id', 'emp_Id', 'manager_Id', 'datetime', 'type', 'status', 'cost', 'requestor_comments', 'approver_comments', 'image_location'].includes(key)) {
                            let listItem = document.createElement('td');
                            listItem.innerText = `${reinbursements[y][key]}`;
                            tr.appendChild(listItem);
                        }
                    }
                    
                    console.log(reinbursements[y]);

                    let listItem5 = document.createElement('td');

                    let LIB1 = document.createElement('button');
                    let LIB2 = document.createElement('button');

                    let ot4 = document.createAttribute("onclick");
                    let ot5 = document.createAttribute("onclick");
                    let ot2 = document.createAttribute("class");
                    let ot3 = document.createAttribute("class");
                    
                    let AD = JSON.stringify(reinbursements[y]);

                    ot4.value = `setApprovedReimbursement(${AD})`;
                    ot5.value = `setDeniedReimbursement(${AD})`;

                    ot2.value = "btn btn-info";
                    ot3.value = "btn btn-info";

                    LIB1.innerText = 'Approved';
                    LIB2.innerText = 'Denied';

                    LIB1.setAttributeNode(ot4);
                    LIB1.setAttributeNode(ot2);
                    LIB2.setAttributeNode(ot5);
                    LIB2.setAttributeNode(ot3);

                    listItem5.appendChild(LIB1);
                    listItem5.appendChild(LIB2);

                    tr.appendChild(listItem5);

                    tb1.appendChild(tr);

                    console.log('hello11');
                }

                if (reinbursements[y]['status'] === 'Approved' || reinbursements[y]['status'] === 'Denied') {
                    for (let key in reinbursements[y]) {
                        if (['id', 'emp_Id', 'manager_Id', 'datetime', 'type', 'status', 'cost', 'requestor_comments', 'approver_comments', 'image_location'].includes(key)) {
                            let listItem = document.createElement('td');
                            listItem.innerText = `${reinbursements[y][key]}`;
                            tr.appendChild(listItem);
                            console.log('hello2');
                        }
                    }
                    tb2.appendChild(tr);
                    console.log('hello22');
                }
            }
        })
        .catch(console.error)
}

async function getEmployeeInformation(i) {
    await fetch(`http://localhost:8080/project-1-dannyr3/api/employees/${i}`)
        .then((Response) => {
            return Response.json()
        }).then((employee) => {
            if (employee != null) {
                uname.placeholder = employee['username'];
                passw.placeholder = employee['password'];
                fname.placeholder = employee['firstName'];
                lname.placeholder = employee['lastName'];
            }
        })
        .catch(console.error)
};

async function getAllReinbursements() {
    while (tb1.hasChildNodes()) {
        tb1.removeChild(tb1.firstChild);
    }

    while (tb2.hasChildNodes()) {
        tb2.removeChild(tb2.firstChild);
    }

    await fetch(`http://localhost:8080/project-1-dannyr3/api/reinbursements`)
        .then((Response) => {
            return Response.json()
        })
        .then((reinbursements) => {
            for (let y = 0; y < reinbursements.length; y++) {
                //let temp = reinbursements[y];
                // Rid Eid D&T Type Status Price RC I
                //Rid Eid Mid D&T Type Status Price RC AC I
                let tr = document.createElement('tr');

                if (reinbursements[y]['status'] === 'Pending') {
                    for (let key in reinbursements[y]) {
                        if (['id', 'emp_Id', 'manager_Id', 'datetime', 'type', 'status', 'cost', 'requestor_comments', 'approver_comments', 'image_location'].includes(key)) {
                            let listItem = document.createElement('td');	
                            listItem.innerText = `${reinbursements[y][key]}`;
                            tr.appendChild(listItem);
                            
                        }
                    }
                    
                    console.log(reinbursements[y]);

                    let listItem5 = document.createElement('td');

                    let LIB1 = document.createElement('button');
                    let LIB2 = document.createElement('button');

                    let ot4 = document.createAttribute("onclick");
                    let ot5 = document.createAttribute("onclick");
                    let ot2 = document.createAttribute("class");
                    let ot3 = document.createAttribute("class");

                    let AD = JSON.stringify(reinbursements[y]);

                    ot4.value = `setApprovedReimbursement(${AD})`;
                    ot5.value = `setDeniedReimbursement(${AD})`;

                    ot2.value = "btn btn-info";
                    ot3.value = "btn btn-info";

                    LIB1.innerText = 'Approved';
                    LIB2.innerText = 'Denied';

                    LIB1.setAttributeNode(ot4);
                    LIB1.setAttributeNode(ot2);
                    LIB2.setAttributeNode(ot5);
                    LIB2.setAttributeNode(ot3);

                    listItem5.appendChild(LIB1);
                    listItem5.appendChild(LIB2);

                    tr.appendChild(listItem5);

                    tb1.appendChild(tr);

                }

                if (reinbursements[y]['status'] === 'Approved' || reinbursements[y]['status'] === 'Denied') {
                    for (let key in reinbursements[y]) {
                        if (['id', 'emp_Id', 'manager_Id', 'datetime', 'type', 'status', 'cost', 'requestor_comments', 'approver_comments', 'image_location'].includes(key)) {
                            let listItem = document.createElement('td');
                            listItem.innerText = `${reinbursements[y][key]}`;
                            tr.appendChild(listItem);

                        }
                    }
                    tb2.appendChild(tr);

                }
            }
        })
        .catch(console.error)
    
};

async function getEmployees() {
    await fetch(`http://localhost:8080/project-1-dannyr3/api/employees`)
        .then((Response) => {
            return Response.json()
        })
        .then((employees) => {

            let tr1 = document.createElement('tr');

            for (let z = 0; z < 5; z++) {

                let LI = document.createElement('td');
                let ot = document.createAttribute("onclick");
                ot.value = `getAllReinbursements()`;
                LI.setAttributeNode(ot);
                LI.innerText = 'ALL';
                tr1.appendChild(LI);

            }

            tb3.appendChild(tr1);

            for (let y = 0; y < employees.length; y++) {
                let tr = document.createElement('tr');
                let idd = employees[y]['id'];
                //let oc = document.createAttribute("onclick");
                //oc.value = `getReinbursementsEmployeeId(${idd})`;
                //tr.setAttributeNode(oc);
                //tr.onclick = `getReinbursementsEmployeeId(${idd})`



                for (let key in employees[y]) {
                    if (['id', 'username', 'password', 'firstName', 'lastName'].includes(key)) {
                        let listItem = document.createElement('td');
                        let oc = document.createAttribute("onclick");
                        oc.value = `getReinbursementsEmployeeId(${idd})`;
                        listItem.setAttributeNode(oc);
                        listItem.innerText = `${employees[y][key]}`;
                        tr.appendChild(listItem);
                    }
                }
                tb3.appendChild(tr);
            }


        })
        .catch(console.error)
}


document.body.onload = () => {
    console.log('onload');
    //getReinbursementsEmployeeId(i);
    getEmployeeInformation(i);
    getEmployees();
    getAllReinbursements();
};