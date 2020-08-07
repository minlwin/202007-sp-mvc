$(() => {
	
	$('.ui.modal').modal({
		autofocus: false,
		closable: false
	})
	
	$('#addBtn').click(() => {
		
		$('#modalTitle').text('Add New Online Class')
		
		// clear inputs
		
		$('.ui.modal').modal('show')
	})
	
	$('.ui.dropdown').dropdown()
	
	$('#code').change(() => {
		let code = $('#code').val()
		let url = `/public/courses/${code}`
		$.get(url, data => {
			$('#fees').val(data.fees)
			$('#duration').val(data.duration)
			$('#requirements').val(data.requirements)
		})
	})
	
	
	$('#teacherSelect').change(() => {
		let teacherId = $('#teacherSelect').val()
		let url = `/public/courses/teacher/${teacherId}`
		
		$.get(url, list => {
			$('#courseInput').val('')
			$('#courses').empty()
			
			list.forEach(data => {
				$("<option>").text(data.name).val(data.code).appendTo($('#courses'))
			})
		})
	})
})


function editClass(id) {
	let url = `/public/classes/${id}`
	
	$.get(url, dto => {
		$('#id').val(dto.id)
		$('#code').val(dto.code)
		$('#startDate').val(dto.startDate)
		$('#fees').val(dto.fees)
		$('#duration').val(dto.duration)
		$('#requirements').val(dto.requirements)
		$('#days').val(dto.days)
		$('#times').val(dto.times)
		
		$('#modalTitle').text("Edit Online Class")
		
		$('.ui.modal').modal('show')
	})
}
