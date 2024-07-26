/*// 비동기 삭제
function delete_policy(event, rule_no) {
    event.preventDefault();

    $.ajax({
        type: 'POST'
        , url: '/delete_policy'
        , contentType: 'application/json'
        , data: JSON.stringify({ rule_no:rule_no })
        , success: function () {
            if (confirm("변경 하시겠습니까?")) {
                let del_pol = event.target.parentNode.parentNode;
                del_pol.remove();
				let but = event.target.parentNode;
				
                alert("변경 되었습니다.");
            }
        }
        , error: function (xhr, status, error) {
            console.log('Error: ', error);
        }
    })
}*/

function update_activation(event, rule_no) {
	event.preventDefault();
	
	$.ajax({
		type: 'POST'
		, url: '/update_activation'
		, contentType: 'application/json'
		, data: JSON.stringify({ rule_no:rule_no})
		, success: function() { 
			if(confirm("변경 하시겠습니까?") ) {
				let target =  event.target;
				
				let actCell = event.target.parentNode.parentNode.childNodes[3];
				if ( actCell.textContent == "비활성화") {
					actCell.textContent = "활성화";
					target.setAttribute('value', "비활성화");
				} else {
					actCell.textContent = "비활성화";
					target.setAttribute('value', "활성화");
				}
				
				alert("변경 되었습니다.");
			}	
		}
		, error: function (xhr, status, error) {
		            console.log('Error: ', error);
		       	}
	})
	
}