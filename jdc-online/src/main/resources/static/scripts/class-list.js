$(() => {
	
	$('.ui.modal').modal({
		autofocus: false,
		closable: false
	})
	
	$('#addBtn').click(() => {
		
		$('#modalTitle').text('Add New Online Class')
		
		// clear inputs
		$('.content.ui.form input').val("")
		$('.content.ui.form textarea').val("")
		$('#id').val('0')
		$('#input-row').show()
		$('#label-row').hide()
		
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
		
		let month = dto.startDate[1]
		let monthStr = month < 10 ? "0" + month : month
		let date = `${dto.startDate[0]}-${monthStr}-${dto.startDate[2]}`
		
		$('#id').val(dto.id)
		$('#code').val(dto.code)
		$('#startDate').val(date)
		$('#fees').val(dto.fees)
		$('#duration').val(dto.duration)
		$('#requirements').val(dto.requirements)
		$('#days').val(dto.days)
		$('#times').val(dto.times)
		
		$('#modalTitle').text("Edit Online Class")
		
		$('#lblTeacher').val(dto.teacher)
		$('#lblCourse').val(dto.course)
		
		$('#input-row').hide()
		$('#label-row').show()

		$('.ui.modal').modal('show')
	})
}

