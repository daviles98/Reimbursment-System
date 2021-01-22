

window.onload = function() {
	getUserReimburs();
}



function getUserReimburs() {
	fetch(
			'http://localhost:8099/ProjectOne/api/ajax/userReimburs')
			.then(function(daResponse) {
				const convertedResponse = daResponse.json();
				return convertedResponse;
			}).then(function(daSecondResponse) {
				console.log(daSecondResponse);
				myUserInformation=daSecondResponse;
				ourDOMManipulation2(daSecondResponse);
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

function checkResolved(resolved) {
	if(resolved === null){
		return '-';
	}else{
		return new Date(resolved).toLocaleString();
	}
}


function ourDOMManipulation2(ourJSON) {

	for (let i = 0; i < ourJSON.length; i++) {
		
		let newTR = document.createElement("tr");
		let newTH = document.createElement("th");
		
		let newTD1 = document.createElement("td");
		let newTD2 = document.createElement("td");
		let newTD3 = document.createElement("td");
		let newTD4 = document.createElement("td");
		let newTD5 = document.createElement("td");
		
		newTH.setAttribute("scope", "row")
		let myText1 = document.createTextNode('$' + ourJSON[i].amount);
		let myText2 = document.createTextNode(new Date(ourJSON[i].submitted).toLocaleString());
		let myText3 = document.createTextNode(checkResolved(ourJSON[i].resolved));	
		let myText4 = document.createTextNode(ourJSON[i].description);
		let myText5 = document.createTextNode(typeLookup(ourJSON[i].reimburType));
		let myText6 = document.createTextNode(statusLookup(ourJSON[i].reimburStatus));
		
		newTH.appendChild(myText1);
		newTD1.appendChild(myText2);
		newTD2.appendChild(myText3);
		newTD3.appendChild(myText4);
		newTD4.appendChild(myText5);
		newTD5.appendChild(myText6);
		
		newTR.appendChild(newTH);
		newTR.appendChild(newTD1);
		newTR.appendChild(newTD2);
		newTR.appendChild(newTD3);
		newTR.appendChild(newTD4);
		newTR.appendChild(newTD5);
		let newSelectionTwo = document.querySelector("#userTableBody2");
		newSelectionTwo.appendChild(newTR);

	}
}

var exampleModal = document.getElementById('exampleModal')
exampleModal.addEventListener('show.bs.modal', function (event) {
  var button = event.relatedTarget

  var recipient = button.getAttribute('data-bs-whatever')

  var modalTitle = exampleModal.querySelector('.modal-title')
  var modalBodyInput = exampleModal.querySelector('.modal-body input')

  modalTitle.textContent = 'Add New Reimbursment';

})




