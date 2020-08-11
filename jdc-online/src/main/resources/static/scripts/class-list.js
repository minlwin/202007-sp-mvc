$(() => {
	
	$('.ui.modal').modal({
		autofocus: false,
		closable: false
	})
	
	$('#addBtn').click(() => {
		$('#createForm').modal('show')
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
		
		$('#e_id').val(dto.id)
		$('#e_code').val(dto.code)
		$('#e_startDate').val(date)
		$('#e_fees').val(dto.fees)
		$('#e_duration').val(dto.duration)
		$('#e_requirements').val(dto.requirements)
		$('#e_days').val(dto.days)
		$('#e_times').val(dto.times)
		
		$('#e_teacher').val(dto.teacher)
		$('#e_course').val(dto.course)
		
		$('#editForm').modal('show')
	})
}

