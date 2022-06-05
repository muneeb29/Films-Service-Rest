
function jsonAll() {
	$('.jsonBt').click(function(e) {

		title = $('#searchName').val();
		$.ajax({
			type: "GET",
			url: 'Rest',
			data: 'format=json' + '&searchTitle=' + title,
			dataType: 'json',
			success: function(data) {
				console.log(data);
				var str = '';


				var hd = '';

				hd +=

					"<table>" +

					"<tr>" +
					"<th>ID</th>" +
					"<th>Title</th>" +
					"<th>Year</th>" +
					"<th>Director</th>" +
					"<th>Stars</th>" +
					"<th>Review</th>" +
					"</tr>" +

					"</table>"

				for (var i = 0; i < data.length; i++) {
					str += ("<table>" + '<tr><td>' + data[i].id + '</td><td>' + data[i].title + '</td><td>' + data[i].year
						+ '</td>+<td>' + data[i].director + '</td><td>' + data[i].stars +
						'</td><td>' + data[i].review + '</td></tr>' + "</table>")

				}
				$('#myTable').html(hd + str);
			},
			error: function(errorThrown) {
				alert('Error: ' + errorThrown);
			}
		});
	});

}


function xmlAll() {
	$('.xmlBt').click(function() {
		title = $('#searchName').val();
		$.ajax({
			type: 'GET',
			url: 'Rest',
			data: 'format=xml' + '&searchTitle=' + title,
			dataType: 'xml',
			success: function(xml) {
				console.log(xml);
				var str = '';

				var hd = '';

				hd +=

					"<table>" +

					"<tr>" +
					"<th>ID</th>" +
					"<th>Title</th>" +
					"<th>Year</th>" +
					"<th>Director</th>" +
					"<th>Stars</th>" +
					"<th>Review</th>" +
					"</tr>" +

					"</table>"

				$(xml).find('film').each(function() {

					str +=
						("<table>" + '<tr>' + '<td>' +
							$(this).find('id').text() + '</td> <td>' +
							$(this).find('title').text() + '</td> <td>' +
							$(this).find('year').text() + '</td> <td>' +
							$(this).find('director').text() + '</td><td>' +
							$(this).find('stars').text() + '</td> <td>' +
							$(this).find('review').text() + '</td> ' +
							'</tr>' + "</table>")
				});
				$('#myTable').html(hd + str);
			}
		});
	});

}


function getText() {
	$('.txtBt').click(function() {
		title = $('#searchName').val();

		var f;


		$.ajax({
			type: 'GET',
			url: 'Rest',
			async: true,
			data: 'format=' + 'text' + '&searchTitle=' + title,
			success: function(data) {
				console.log(data);

				f = data.split("+");
				numberOfFilmAttributes = parseInt(f.length);


				var str = '';
				var hd = '';

				hd +=

					"<table>" +

					"<tr>" +
					"<th>Review</th>" +
					"<th>ID</th>" +
					"<th>Title</th>" +
					"<th>Year</th>" +
					"<th>Director</th>" +
					"<th>Stars</th>" +
					"</tr>" +

					"</table>"

				for (var i = 0; i < f.length; i++) {
					str += ("<table>" + '<tr>' +
						'<td>' + f[i]
						+ '</td><td>' + f[i = i + 1] + '</td><td>' + f[i = i + 1]
						+ '</td>+<td>' + f[i = i + 1] + '</td><td>' + f[i = i + 1] +
						'</td><td>' + f[i = i + 1] + '</td></tr>' + "</table>")

				}
				$('#myTable').html(hd + str);
			},
		});



	});
}



function insertjsonfilm() {
	$('#insertBtJson').click(function(e) {
		e.preventDefault();
		var fList = {
			id: $('#insertID').val(),
			title: $('#insertTitle').val(),
			year: $('#insertYear').val(),
			director: $('#insertDirector').val(),
			stars: $('#insertStars').val(),
			review: $('#insertReview').val()
		}

		$.ajax({
			type: 'POST',
			url: 'Rest?' + 'format=json',
			dataType: 'json',
			data: JSON.stringify(fList),
			contentType: "application/json; charset=utf-8",
			success: function(data) {
				console.log(data)
			}
		});



	});

}


function insertFilm() {
	$('#insertBtTxt').click(function(e) {
		e.preventDefault();
		id = $('#insertID').val();
		title = $('#insertTitle').val();
		year = $('#insertYear').val();
		director = $('#insertDirector').val();
		stars = $('#insertStars').val();
		review = $('#insertReview').val();

		$.ajax({
			type: 'POST',
			url: 'Rest?format=text',
			data: 'idIn=' + id + '&titleIn=' + title + '&yearIn=' + year + '&dirIn=' + director + '&starsIn=' + stars + '&reviewIn=' + review,
			dataType: 'text',
		});

		$("#insertFilmMes").html("Film Inserted:"
			+ "<ul>" +
			"<li>" + "ID : " + id + "<li>" +
			"<li>" + "Title : " + title + "<li>" +
			"<li>" + "Year : " + year + "<li>" +
			"<li>" + "Director : " + director + "<li>" +
			"<li>" + "Stars : " + stars + "<li>" +
			"<li>" + "Review : " + review + "<li>" +
			"</ul>")

	});

}

function insertXML() {

	$('#insertBtXml').click(function(e) {
		e.preventDefault();
		id = $('#insertID').val();
		title = $('#insertTitle').val();
		year = $('#insertYear').val();
		director = $('#insertDirector').val();
		stars = $('#insertStars').val();
		review = $('#insertReview').val();


		var xmlfilm = '<film><id>' + id +
			'</id><title>' + title +
			'</title><year>' + year
			+ '</year><director>' + director
			+ '</director><stars>' + stars
			+ '</stars><review>' + review + '</review></film>';
		console.log(xmlfilm);

		$.ajax({
			type: 'POST',
			url: 'Rest?format=xml',
			dataType: 'xml',
			data: xmlfilm,
			contentType: 'application/xml',
			success: function(data) {
				console.log(data);
			}


		});


	});

}


function deleteJson() {
	$('#deleteBtJson').click(function() {

		id = $('#deleteFilm').val();
		var deleteList = {
			id: $('#deleteFilm').val(),
		}

		$.ajax({
			type: 'DELETE',
			url: 'Rest?format=json',
			dataType: 'json',
			data: JSON.stringify(deleteList),
			success: function(data) {
				console.log(data);
			}
		});
		$('#deleteFilmMes').html("Film: " + id + " is Deleted");
	});

}

function deleteFilm() {
	$('#deleteBtTxt').click(function() {
		id = $('#deleteFilm').val();
		console.log(id);

		$.ajax({
			type: 'DELETE',
			url: 'Rest?format=text' + '&id=' + id,
			dataType: 'text',

		});
		$('#deleteFilmMes').html("Film: " + id + " is Deleted");

	});

}

function deleteXML() {
	$('#deleteBtXml').click(function() {
		id = $('#deleteFilm').val();

		var deletexml = '<film><id>' + id + '</id></film>';
		console.log(deletexml);
		$.ajax({
			type: 'DELETE',
			url: 'Rest?format=xml',
			dataType: 'xml',
			data: deletexml,
			contentType: 'application/xml'
		})
		$('#deleteFilmMes').html("Film: " + id + " is Deleted");

	});
}

function updatejsonFilm() {
	$('#updateBtJson').click(function() {

		id = $('#updateID').val();
		title = $('#updateTitle').val();
		year = $('#updateYear').val();
		director = $('#updateDirector').val();
		stars = $('#updateStars').val();
		review = $('#updateReview').val();

		var updateList = {
			id: $('#updateID').val(),
			title: $('#updateTitle').val(),
			year: $('#updateYear').val(),
			director: $('#updateDirector').val(),
			stars: $('#updateStars').val(),
			review: $('#updateReview').val()
		}
		$.ajax({
			type: 'PUT',
			url: 'Rest?format=json',
			dataType: 'json',
			data: JSON.stringify(updateList),
			success: function(data) {
				console.log(data);
			}
		})
		$("#updateFilmMes").html("Film Inserted:"
			+ "<ul>" +
			"<li>" + "ID : " + id + "<li>" +
			"<li>" + "Title : " + title + "<li>" +
			"<li>" + "Year : " + year + "<li>" +
			"<li>" + "Director : " + director + "<li>" +
			"<li>" + "Stars : " + stars + "<li>" +
			"<li>" + "Review : " + review + "<li>" +
			"</ul>")

	});
}



function updateFilm() {
	$('#updateBtTxt').click(function() {
		id = $('#updateID').val();
		title = $('#updateTitle').val();
		year = $('#updateYear').val();
		director = $('#updateDirector').val();
		stars = $('#updateStars').val();
		review = $('#updateReview').val();

		$.ajax({
			type: 'PUT',
			url: 'Rest?format=text' + '&upID=' + id + '&upTitle=' + title + '&upYear=' + year + '&upDirector=' + director + '&upStars=' + stars + '&upReview=' + review,
			dataType: 'text',
		});


		$("#updateFilmMes").html("Film Inserted:"
			+ "<ul>" +
			"<li>" + "ID : " + id + "<li>" +
			"<li>" + "Title : " + title + "<li>" +
			"<li>" + "Year : " + year + "<li>" +
			"<li>" + "Director : " + director + "<li>" +
			"<li>" + "Stars : " + stars + "<li>" +
			"<li>" + "Review : " + review + "<li>" +
			"</ul>")


	});
}

function updateXML() {
	$('#updateBtXml').click(function() {
		id = $('#updateID').val();
		title = $('#updateTitle').val();
		year = $('#updateYear').val();
		director = $('#updateDirector').val();
		stars = $('#updateStars').val();
		review = $('#updateReview').val();

		var updatexml = '<film><id>' + id +
			'</id><title>' + title +
			'</title><year>' + year
			+ '</year><director>' + director
			+ '</director><stars>' + stars
			+ '</stars><review>' + review + '</review></film>';

		$.ajax({
			url: 'Rest?format=xml',
			type: 'PUT',
			dataType: 'xml',
			data: updatexml,
			contentType: 'application.xml'
		})

		$("#updateFilmMes").html("Film Inserted:"
			+ "<ul>" +
			"<li>" + "ID : " + id + "<li>" +
			"<li>" + "Title : " + title + "<li>" +
			"<li>" + "Year : " + year + "<li>" +
			"<li>" + "Director : " + director + "<li>" +
			"<li>" + "Stars : " + stars + "<li>" +
			"<li>" + "Review : " + review + "<li>" +
			"</ul>")

	});
}


$(document).ready(function() {


	jsonAll();
	xmlAll();
	getText();

	insertjsonfilm();
	updatejsonFilm();
	deleteJson();

	insertXML();
	deleteXML();
	updateXML();

	insertFilm();
	deleteFilm();
	updateFilm();

});





