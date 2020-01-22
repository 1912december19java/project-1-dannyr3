'use strict';

let body = document.getElementsByTagName("body");
let tb1 = document.getElementById("tb-1");
let tb2 = document.getElementById("tb-2");
let uname = document.getElementById("username");
let passw = document.getElementById("password");
let fname = document.getElementById("firstName");
let lname = document.getElementById("lastName");
let esubmit = document.getElementById("e-submit");

let rform = document.getElementById("r-form");
let rtype = document.getElementById("r-type");
let rcost = document.getElementById("r-cost");
let rcomment = document.getElementById("r-comment");
let rimage = document.getElementById("r-image");
let rempid = document.getElementById("r-empId");
let rstatus = document.getElementById("r-status");
let rsubmit = document.getElementById("r-submit");



const queryString = window.location.search;

const urlParams = new URLSearchParams(queryString);

const i = urlParams.get('Id')
console.log(i);
console.log('1');

rsubmit.addEventListener('click', (e)=>{
	
	e.preventDefault();
	sendReimbursement();

});

esubmit.addEventListener('click', (e)=>{
	
	e.preventDefault();
	updateInfo();
	
});

async function updateInfo(){
	
let myUri = "http://localhost:8080/project-1-dannyr3/api/employees";
	
	let employee = {};
	employee.id = i;
	employee.username = uname.value;
	employee.password = passw.value;
	employee.firstName = fname.value;
	employee.lastName = lname.value;
	
	let response = await fetch(myUri, {method: 'POST', body: JSON.stringify(employee)});
	
	uname.placeholder = uname.value;
	passw.placeholder = passw.value;
	fname.placeholder = fname.value;
	lname.placeholder = lname.value;
}


async function sendReimbursement(){
	
	let myUri = "http://localhost:8080/project-1-dannyr3/api/reinbursements";
    
    let file = rimage.files[0];
    console.log(file);
    console.log(file.toString());


    let blob;
    let fileReader = new FileReader();
    fileReader.readAsDataURL(file);
    blob = fileReader.result;
    console.log(blob[0]);


	let reimbursement = {};
	reimbursement.emp_Id = i;
	reimbursement.id = 0;
	reimbursement.manager_Id = null;
	reimbursement.datetime = "";
	reimbursement.type = rtype.value;
	reimbursement.status = rstatus.value;
	reimbursement.cost = rcost.value;
	reimbursement.requestor_comments = rcomment.value;
	reimbursement.approver_comments = "";
    reimbursement.image_location = rimage.value;
    
	
	let response = await fetch(myUri, {method: 'POST', body: JSON.stringify(reimbursement)});
}


async function getReinbursementsEmployeeId(i){
    await fetch(`http://localhost:8080/project-1-dannyr3/api/reinbursements/${i}`)
    .then((Response)=>{
        return Response.json()
    })
    .then((reinbursements)=>{
        for (let y = 0; y < reinbursements.length; y++)
        {
            //let temp = reinbursements[y];
            // Rid Eid D&T Type Status Price RC I
            //Rid Eid Mid D&T Type Status Price RC AC I
            let tr = document.createElement('tr');
            console.log('we in there');

            if(reinbursements[y]['status'] === 'Pending'){
                for(let key in reinbursements[y]){
                    if (['id','emp_Id', 'datetime', 'type', 'status', 'cost', 'requestor_comments', 'image_location'].includes(key)){
                        let listItem = document.createElement('td');
                        listItem.innerText = `${reinbursements[y][key]}`;
                        tr.appendChild(listItem);
                        console.log('hello1');
                    }
                }
                tb1.appendChild(tr);
                console.log('hello11');
            }

            if(reinbursements[y]['status'] === 'Approved' || reinbursements[y]['status'] === 'Denied'){
                for(let key in reinbursements[y]){
                    if (['id','emp_Id', 'manager_Id', 'datetime', 'type', 'status', 'cost', 'requestor_comments', 'approver_comments', 'image_location'].includes(key)){
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

async function getEmployeeInformation(i){
	await fetch(`http://localhost:8080/project-1-dannyr3/api/employees/${i}`)
    .then((Response)=>{
        return Response.json()
    }).then((employee)=>{
    	if (employee != null){
    		uname.placeholder = employee['username'];
    		passw.placeholder = employee['password'];
    		fname.placeholder = employee['firstName'];
    		lname.placeholder = employee['lastName'];
    	}
    })
	.catch(console.error)
};


document.body.onload = ()=>{
	console.log('onload');
    getReinbursementsEmployeeId(i);
    getEmployeeInformation(i);
};