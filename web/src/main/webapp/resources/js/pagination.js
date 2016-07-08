/** 分页 */
$(document).ready(function() {
	$('.paginationPanel .pagination li').click(function() {
		var $this = $(this);
		if ($this.hasClass('disabled') || $this.hasClass('active')) {
			return false;
		} else {
			var pageNumber = $this.attr('page');
			var formid = $this.parents('.paginationPanel').attr('form-id');
			var $form = $('#' + formid);
			$form.find('input[name=current]').val(pageNumber);
			$form.submit();
		}
	});

	$('.paginationPanel .gotoPage').click(function() {
		var $pageSize = $(this).siblings('.pageSize');
		var $pageNumber = $(this).siblings('.pageNumber');
		var _pageSize = $pageSize.val();
		var _pageNumber = $pageNumber.val();
		var pattern = /^[0-9]+$/;
		var formid = $(this).parents('.paginationPanel').attr('form-id');
		var $form = $('#' + formid);
		var forward = true;
		if ($.trim(_pageSize) && pattern.test(_pageSize)) {
			_pageSize = parseInt(_pageSize);
			if (_pageSize < 5) {
				$pageSize.focus();
				alert('请输入大于等于5的整数');
				forward = false;
			} else {
				$form.find('input[name=pageSize]').val(_pageSize);
			}
		}

		if ($.trim(_pageNumber) && pattern.test(_pageNumber)) {
			_pageNumber = parseInt(_pageNumber);
			if (_pageNumber < 1) {
				$pageNumber.focus();
				alert('请输入大于等于1的整数');
				forward = false;
			} else {
				$form.find('input[name=current]').val(_pageNumber);
			}
		}

		if (forward) {
			$form.submit();
		} else {
			return false;
		}
	});
});