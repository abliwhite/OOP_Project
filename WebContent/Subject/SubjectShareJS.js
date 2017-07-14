/**
 * 
 */

function fadeAlertMessage(id) {
	$("#"+id).fadeTo(1800, 600).slideUp(600, function() {
		$("#"+id).slideUp(600);
	});
}

function buildNewComponentTemplateTable(arg) {
	var templatesDiv = $("#subjectComponents_id");

	templatesDiv.empty();

	var templates = document.createElement('table');

	templates.setAttribute("class", "table");
	var thead = document.createElement('thead');
	// thead.setAttribute("class","thead-inverse");
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

	arg.forEach(function createComponentTemplateTableRow(args) {
		console.log(args);
		console.log(args.id)
		var tr = document.createElement('tr');
		tr.setAttribute("id", args.id);
		tr.setAttribute("class", "info");

		var nameTd = document.createElement('td');
		var name = document.createElement("label");
		name.setAttribute("id", "subjectComponentNameInput_" + args.id);
		name.innerHTML = args.subjectComponentType.name;
		nameTd.append(name);

		var numberTd = document.createElement('td');
		var number = document.createElement("label");
		//number.setAttribute("type", "number");
		//number.setAttribute("value", args.number);
		number.setAttribute("id", "subjectComponentTemplateNumberInput_"
				+ args.id);
		//number.setAttribute("class", "form-control");
		//number.setAttribute("placeholder", "Number");
		//number.setAttribute("disabled",true);
		number.innerHTML = args.number;
		numberTd.append(number);

		var percentageTd = document.createElement('td');
		var percentage = document.createElement("label");
		percentage.setAttribute("id",
				"subjectComponentTemplatePercentageInput_" + args.id);
		percentage.innerHTML = args.markPercentage;
		percentageTd.append(percentage);

		var removeEditTd = document.createElement('td');
		var remove = document.createElement("INPUT");
		remove.setAttribute("type", "button");
		remove.setAttribute("value", "Delete");
		remove.setAttribute("onclick", "deleteComponentTemplate(" + args.id
				+ "); return false;");
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

function addTemplate() {
	
	typeId = $("#subjectComponentNameSelect_id").val();
	componentPercentage = $("#subjectComponentTemplatePercentageInput_id")
			.val();
	componentNumber = $("#subjectComponentTemplateNumberInput_id").val();
	subjectId = $("#hidden_subject_id").val();

	if (componentPercentage == "" || componentNumber == "") {
		$("#subject_component_alert_div_id").removeClass("alert alert-success");
		$("#subject_component_alert_div_id").addClass("alert alert-danger");
		$("#subject_component_alert_div_id").html("Fill All Fields!");
		$("#subject_component_alert_div_id").show();

		fadeAlertMessage("subject_component_alert_div_id");
		return;
	}

	data = {
		subjectId : subjectId,
		typeId : typeId,
		percentage : componentPercentage,
		number : componentNumber,
	};

	$.ajax({
		type : "POST",
		url : "/AddCommonSubjectComponentServlet",
		contentType : "application/json",
		data : JSON.stringify(data),
		success : function(response) {
			console.log(response);
			if (!response.isSuccess) {
				$("#subject_component_alert_div_id").removeClass(
						"alert alert-success");
				$("#subject_component_alert_div_id").addClass(
						"alert alert-danger");
				$("#subject_component_alert_div_id").html(
						response.resultMessage);
				$("#subject_component_alert_div_id").show();
				
				fadeAlertMessage("subject_component_alert_div_id");
				return;
			}

			$("#subject_component_alert_div_id").removeClass(
					"alert alert-danger");
			$("#subject_component_alert_div_id")
					.addClass("alert alert-success");
			$("#subject_component_alert_div_id").html(response.resultMessage);
			$("#subject_component_alert_div_id").show();

			fadeAlertMessage("subject_component_alert_div_id");
			buildNewComponentTemplateTable(response.resultList);
		}
	});
}

function deleteComponentTemplate(id) {

	data = {
		id : id,
	};

	$.ajax({
		type : "POST",
		url : "/DeleteCommonSubjectComponentServlet",
		contentType : "application/json",
		data : JSON.stringify(data),
		success : function(response) {
			// buildNewComponentTemplateTable(response);
			$("#" + id).remove();
		}
	});
}