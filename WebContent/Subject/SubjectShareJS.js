/**
 * 
 */

function buildNewComponentTemplateTable(arg){
		var templatesDiv = $("#subjectComponents_id");
		
		templatesDiv.empty();
		
		var templates = document.createElement('table');
			
		templates.setAttribute("class","table");
		var thead = document.createElement('thead');
		//thead.setAttribute("class","thead-inverse");
		var tr1 = document.createElement('tr');
		var th1 = document.createElement('th');
		th1.innerText = "Name";
		var th2 = document.createElement('th');
		th2.innerText = "Number";
		var th3 = document.createElement('th');
		th3.innerText = "Percent";
		
		tr1.append(th1);
		tr1.append(th2);
		tr1.append(th3);
		
		thead.append(tr1);
		
		templates.append(thead);
		
		
		
		arg.forEach(function createComponentTemplateTableRow(args){
			console.log(args);
			console.log(args.id)
			var tr = document.createElement('tr');
			tr.setAttribute("id",args.id);
			tr.setAttribute("class","info");
			
			var nameTd = document.createElement('td');
			var name = document.createElement("INPUT");
			name.setAttribute("type", "text");
			name.setAttribute("value",args.name);
			name.setAttribute("id","subjectComponentNameInput_"+args.id);
			name.setAttribute("class","form-control");
			name.setAttribute("placeholder","Name");
			nameTd.append(name);
			
			var numberTd = document.createElement('td');
			var number = document.createElement("INPUT");
			number.setAttribute("type", "number");
			number.setAttribute("value",args.number);
			number.setAttribute("id","subjectComponentTemplateNumberInput_"+args.id);
			number.setAttribute("class","form-control");
			number.setAttribute("placeholder","Number");
			numberTd.append(number);
			
			var percentageTd = document.createElement('td');
			var percentage = document.createElement("INPUT");
			percentage.setAttribute("type", "number");
			percentage.setAttribute("value",args.markPercentage);
			percentage.setAttribute("id","subjectComponentTemplatePercentageInput_"+args.id);
			percentage.setAttribute("class","form-control");
			percentage.setAttribute("placeholder","Percentage");
			percentageTd.append(percentage);		
			
			var removeEditTd = document.createElement('td');
			var edit = document.createElement("INPUT");
			edit.setAttribute("type", "button");
			edit.setAttribute("value","Edit");
		    edit.setAttribute("onclick","EditTemplate("+args.id+"); return false;");
		    edit.setAttribute("class", "btn btn-warning");
		    removeEditTd.append(edit);
		    
		    var remove = document.createElement("INPUT");
		    remove.setAttribute("type", "button");
		    remove.setAttribute("value","Delete");
		    remove.setAttribute("onclick","deleteComponentTemplate("+args.id+"); return false;");
			remove.setAttribute("class", "btn btn-danger");
			removeEditTd.append(remove);
			
	        tr.append(nameTd);
	        tr.append(numberTd);
	        tr.append(percentageTd);
	        tr.append(removeEditTd);

	        templates.append(tr);
		});
		
		templatesDiv.append(templates);
	}

function addTemplate(){
	componentName = $("#subjectComponentNameInput_id").val();
	componentPercentage = $("#subjectComponentTemplatePercentageInput_id").val();
	componentNumber = $("#subjectComponentTemplateNumberInput_id").val();
	subjectId = $("#hidden_subject_id").val();
	
	if(componentName == "" || componentPercentage == "" || componentNumber == ""){
		$("#alert").innerHTML = "Fill All Fields";
		return;
	}
	
	data = {
			subjectId: subjectId,			
			name: componentName,
			percentage: componentPercentage,
			number: componentNumber	
		};
	
	$.ajax({
	    type: "POST",
	    url: "/AddComponentTemplateServlet",
	    contentType: "application/json",
	    data: JSON.stringify(data),
	    success: function(response) {
	    	buildNewComponentTemplateTable(response);
	    }
	});
}

function deleteComponentTemplate(id){
	subjectId = $("#hidden_subject_id").val();
	
	data = {
			id: id,	
			subjectId: subjectId
		};
	
	$.ajax({
	    type: "POST",
	    url: "/DeleteComponentTemplateServlet",
	    contentType: "application/json",
	    data: JSON.stringify(data),
	    success: function(response) {
	    	//buildNewComponentTemplateTable(response);
	    	$("#"+id).remove();
	    }
	});
}