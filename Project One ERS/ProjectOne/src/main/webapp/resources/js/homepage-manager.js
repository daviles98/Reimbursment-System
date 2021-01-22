

window.onload = function() {
	//getAllReimburs();
	//getAllUsers();
	getAll();
}

function getAll(){
	Promise.all([
		fetch('http://localhost:8099/ProjectOne/api/ajax/allReimburs'),
		fetch('http://localhost:8099/ProjectOne/api/ajax/allUsers')
	]).then(function (responses) {
		return Promise.all(responses.map(function (response) {
			return response.json();
		}));
	}).then(function (data) {
		//console.log(data);
		ourDOMManipulation2(data);
	}).catch(function (error) {
		console.log(error);
	});
}


function typeLookup(type) {
	switch(type){
	
		case 1:
			return "Lodging";
		
		case 2:
			return "Travel";
			
		case 3:
			return "Food";
		
		case 4:
			return "Other";
	}
}

function statusLookup(status) {
	switch(status){
	
		case 1:
			return "Pending";
		
		case 2:
			return "Approved";
			
		case 3:
			return "Denied";
	}
}

function findUser(userId, ourJSON) {
	let userInfo = [];
	let name = ourJSON[1][userId-1].firstName + ' ' + ourJSON[1][userId-1].lastName;
	userInfo.push(ourJSON[1][userId-1].username);
	userInfo.push(name);
	userInfo.push(ourJSON[1][userId-1].email);
	
	return userInfo;
}

function checkResolved(resolved) {
	if(resolved === null){
		return '-';
	}else{
		return new Date(resolved).toLocaleString();
	}
}

function ourDOMManipulation2(ourJSON) {

	console.log(ourJSON);


	for (let i = 0; i < ourJSON[0].length; i++) {
	
		let userInfo = findUser(ourJSON[0][i].authorFK, ourJSON);

		
		let newTR = document.createElement("tr");
		let newTH = document.createElement("th");
		
		//let newTD1 = document.createElement("td");
		let newTD2 = document.createElement("td");
		let newTD3 = document.createElement("td");
		let newTD4 = document.createElement("td");
		let newTD5 = document.createElement("td");
		let newTD6 = document.createElement("td");
		let newTD7 = document.createElement("td");
		let newTD8 = document.createElement("td");
		let newTD9 = document.createElement("td");
		let newTD10 = document.createElement("td");
		
		newTH.setAttribute("scope", "row")
		let myText1 = document.createTextNode(userInfo[0]);
		//let myText2 = document.createTextNode(userInfo[1]);
		let myText3 = document.createTextNode(userInfo[2]);
		let myText4 = document.createTextNode(typeLookup(ourJSON[0][i].reimburType));
		let myText5 = document.createTextNode('$' + ourJSON[0][i].amount);
		let myText6 = document.createTextNode(new Date(ourJSON[0][i].submitted).toLocaleString());
		let myText7 = document.createTextNode(checkResolved(ourJSON[0][i].resolved));
		let myText8 = document.createTextNode(ourJSON[0][i].description);
		let myText9 = document.createTextNode(statusLookup(ourJSON[0][i].reimburStatus));
		

		
		newTH.appendChild(myText1);
		//newTD1.appendChild(myText2);
		newTD2.appendChild(myText3);
		newTD3.appendChild(myText4);
		newTD4.appendChild(myText5);
		newTD5.appendChild(myText6);
		newTD6.appendChild(myText7);
		newTD7.appendChild(myText8);
		newTD8.appendChild(myText9);
		
		newTR.appendChild(newTH);
		//newTR.appendChild(newTD1);
		newTR.appendChild(newTD2);
		newTR.appendChild(newTD3);
		newTR.appendChild(newTD4);
		newTR.appendChild(newTD5);
		newTR.appendChild(newTD6);
		newTR.appendChild(newTD7);
		newTR.appendChild(newTD8);
		
		if(ourJSON[0][i].reimburStatus === 1){
		
			let form1 = document.createElement("form");
			let form2 = document.createElement("form");
			let approveBtn = document.createElement("button");
			let deniedBtn = document.createElement("button");
			let inputEmployeeId1 = document.createElement("input");
			let inputEmployeeId2 = document.createElement("input");
			
			form1.method = "post";
			form1.action = "/ProjectOne/api/forward/update";
			form2.method = "post";
			form2.action = "/ProjectOne/api/forward/update";
			approveBtn.className = "btn btn-outline-success btn-lg";
			deniedBtn.className = "btn btn-outline-danger btn-lg";
			approveBtn.name = "status";
			deniedBtn.name = "status";
			approveBtn.type = "submit";
			deniedBtn.type = "submit";
			inputEmployeeId1.type = "hidden";
			inputEmployeeId2.type = "hidden";
			approveBtn.value = "2";
			deniedBtn.value = "3";
			inputEmployeeId1.value = ourJSON[0][i].reimburId;
			inputEmployeeId1.name = 'employeeId';
			inputEmployeeId2.value = ourJSON[0][i].reimburId;
			inputEmployeeId2.name = 'employeeId';
			
			form1.appendChild(approveBtn);
			form1.appendChild(inputEmployeeId1);
			form2.appendChild(deniedBtn);
			form2.appendChild(inputEmployeeId2);
			newTD9.appendChild(form1);
			newTD10.appendChild(form2);
			newTR.appendChild(newTD9);
			newTR.appendChild(newTD10);
		}else{
			let myEmptyText1 = document.createTextNode('');
			let myEmptyText2 = document.createTextNode('');
			newTD9.appendChild(myEmptyText1);
			newTD10.appendChild(myEmptyText2);
			newTR.appendChild(newTD9);
			newTR.appendChild(newTD10);
		}
		
		let newSelectionTwo = document.querySelector("#userTableBody2");
		newSelectionTwo.appendChild(newTR);

	}
}



let reimburTable = document.querySelector("#userTableBody2");

function repopulateTable(){
	for(let i = 0; i < reimburTable.rows.length; i++){
		reimburTable.rows[i].hidden = false;
	}
	console.log(reimburTable);
	console.log(reimburTable.rows[0].cells[7]);
}

document.getElementById("pending").addEventListener("click", function(){
	repopulateTable();
	console.log('Row length:' + reimburTable.rows.length);
	console.log(reimburTable.rows[0].cells);
	console.log(reimburTable.rows[0].cells[7]);
	console.log('Status text: ' + reimburTable.rows[0].cells[7].innerText);

	for(let i = 0; i < reimburTable.rows.length; i++){
		if(reimburTable.rows[i].cells[7].innerText !== "Pending"){
			reimburTable.rows[i].hidden = true;
		}
	}
	console.log(reimburTable.rows.length);
});


document.getElementById("approved").addEventListener("click", function(){
	repopulateTable();
	console.log(reimburTable.rows.length);
	console.log(reimburTable.rows[0].cells);
	console.log(reimburTable.rows[0].cells[7]);
	console.log(reimburTable.rows[0].cells[7].innerText);

	for(let i = 0; i < reimburTable.rows.length; i++){
		if(reimburTable.rows[i].cells[7].innerText !== "Approved"){
			reimburTable.rows[i].hidden = true;
		}
	}
	console.log(reimburTable.rows.length);
});


document.getElementById("denied").addEventListener("click", function(){
	repopulateTable();
	console.log(reimburTable.rows.length);
	console.log(reimburTable.rows[0].cells);
	console.log(reimburTable.rows[0].cells[7]);
	console.log(reimburTable.rows[0].cells[7].innerText);

	for(let i = 0; i < reimburTable.rows.length; i++){
		if(reimburTable.rows[i].cells[7].innerText !== "Denied"){
			reimburTable.rows[i].hidden = true;
		}
	}
	console.log(reimburTable.rows.length);
});


document.getElementById("all").addEventListener("click", function(){
	repopulateTable();
});



