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

$(document).ready(function() {
    $('#filterBtn').click(function() {
        const startDate = $('#startDate').val();
        const endDate = $('#endDate').val();

        $.ajax({
            url: '/Log',
            type: 'GET',
            data: {
                startDate: startDate,
                endDate: endDate
            },
            success: function(data) {
                $('#ips_log_table').html(data);
            }
        })
    })
})
