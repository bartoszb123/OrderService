$(document).ready(function(){


$.ajax({
   			url: 'http://localhost:8081/orderservice/ordersList',
   			method: 'GET',
            mimeType: 'application/json;',
   			success: function(data) {

                 $('#table').bootstrapTable({
                     data: data.arr
                 });

            }
   		});

});



