<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
	integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
	crossorigin="anonymous">
<link
	href="https://fonts.googleapis.com/css?family=Stylish&display=swap"
	rel="stylesheet">
<script src="https://kit.fontawesome.com/76b49c6d9b.js"
	crossorigin="anonymous"></script>
<link rel="stylesheet" href="../resources/css/common.css">
<title>나의 플래너</title>

<style>
#local-area {
	margin-top: 24px;
}

#check-wrapper {
	margin-top: 20px;
}

#search-wrapper {
	min-width: 300px;
}

.planner-wrapper {
	width: 100%;
	position: relative;
}

.planner {
	font-family: 'Stylish';
	padding: 10px;
	width: 25%;
	float: left;
	height: 400px;
}

.pagination-wrapper {
	width: 100%;
	clear: both;
	margin-top: 20px;
}

.card {
	height: 100%;
}

.card-body {
	height: 50%;
}

.card-img-top {
	width: 100%;
	height: 50%;
}

.page-num{
	color: #18a8f1;
}

.page-num:hover{
	cursor: pointer;
}

.displayNone{
	display: none;
}

@media screen and (max-width: 1199px) {
	.planner {
		width: 33%;
	}
}

@media screen and (max-width: 991px) {
	.planner {
		width: 50%;
	}
}

@media screen and (max-width: 506px) {
	.planner {
		width: 100%;
	}
}
</style>

<title>나의 플래너</title>
</head>
<body>
	<jsp:include page="../common/header.jsp" />
	<jsp:include page="../common/nav.jsp" />

	<!-- 나의 플래너 컨텐츠 시작 부분 -->
	<div class="container my-5">
		<h2 class="font-weight-bold">나의 플래너</h2>
		<hr>

		<div class="d-flex btn-wrapper">
			<a href="#" class="btn main-btn ml-auto" type="button">플래너 만들기</a>
		</div>

		<!-- 플래너 컨테이너 -->
		<div class="py-3">
			<h4>수정중인 플래너</h4>

			<!-- 수정중인 플래너 리스트 -->
			<div class="planner-wrapper my-3">
				<c:if test="${empty uPlannerList}">
					<div class="planner" style="height: 250px;">
						수정중인 플래너가 없습니다.
					</div>
				</c:if>
				<c:if test="${!empty uPlannerList}">
					<c:forEach var="planner" items="${uPlannerList}" varStatus="vs">
						<div class="planner<c:if test='${vs.count > 4}'> displayNone</c:if>" id="uPlanner${vs.count}">
							<div class="card">
								<img class="card-img-top" src="${contextPath}/resources/areaImages/${planner.areaNames[0].largeAreaCode}.jpg"
									alt="Card image">
								<div class="card-body">
									<h5 class="card-title">${planner.plannerTitle}</h5>
									<p class="card-text">
										<span>시작일 : ${planner.plannerStartDT} ${planner.tripDate}DAYS</span><br> <span>${planner.groupName} 여행</span><br>
		                                <c:if test="${planner.areaNames.size()>1 }">
		                                	<span>${planner.areaNames[0].largeAreaName}
		                                		  ${planner.areaNames[0].smallAreaName} ...</span>
		                                </c:if>
		                                <c:if test="${planner.areaNames.size()==1 }">
		                                	<span>${planner.areaNames[0].largeAreaName}
		                                		  ${planner.areaNames[0].smallAreaName} </span>
		                                </c:if>
		                                <c:if test="${planner.areaNames.size()<1 }">
		                                	<span>없음 </span>
		                                </c:if>
									</p>
									<div class="d-flex justify-content-between">
										<div class="btn-wrapper">
											<input type="hidden" name="plannerNo" value="${planner.plannerNo}">
											<button type="button" class="btn btn-sm main-btn">바로가기</button>
											<c:if test="${planner.plannerPermission eq '3'}">
												<button type="button" class="btn btn-sm del-btn planner-delete">삭제</button>
											</c:if>
										</div>
										<div>
											<i class="fas fa-eye"></i>&nbsp;${planner.plannerCount}
										</div>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</c:if>

			</div>
			
			<script>
				var limit = 4;
				var pagingBarSize = 5;
				
				var uPlannerSize = ${uPlannerList.size()};
				var uCurrentPage = 1;
			</script>

			<!-- 페이징바 -->
			<div class="pagination-wrapper" id="uPlannerPaging">
				<nav aria-label="Page navigation">
					<ul class="pagination justify-content-center"></ul>
				</nav>
			</div>
		</div>

		<!-- 플래너 컨테이너 -->
		<div class="py-3">
			<h4>완료된 플래너</h4>

			<!-- 완료된 플래너 리스트 -->
			<div class="planner-wrapper my-3">

				<c:if test="${empty cPlannerList}">
					<div class="planner" style="height: 250px;">
						완료된 플래너가 없습니다.
					</div>
				</c:if>
				<c:if test="${!empty cPlannerList}">
					<c:forEach var="planner" items="${cPlannerList}" varStatus="vs">
						<div class="planner<c:if test='${vs.count > 4}'> displayNone</c:if>" id="cPlanner${vs.count}">
							<div class="card">
								<img class="card-img-top" src="${contextPath}/resources/areaImages/${planner.areaNames[0].largeAreaCode}.jpg"
									alt="Card image">
								<div class="card-body">
									<h5 class="card-title">${planner.plannerTitle}</h5>
									<p class="card-text">
										<span>시작일 : ${planner.plannerStartDT} ${planner.tripDate}DAYS</span><br> <span>${planner.groupName} 여행</span><br>
		                                <c:if test="${planner.areaNames.size()>1 }">
		                                	<span>${planner.areaNames[0].largeAreaName}
		                                		  ${planner.areaNames[0].smallAreaName} ...</span>
		                                </c:if>
		                                <c:if test="${planner.areaNames.size()==1 }">
		                                	<span>${planner.areaNames[0].largeAreaName}
		                                		  ${planner.areaNames[0].smallAreaName} </span>
		                                </c:if>
		                                <c:if test="${planner.areaNames.size()<1 }">
		                                	<span>없음 </span>
		                                </c:if>
									</p>
									<div class="d-flex justify-content-between">
										<div class="btn-wrapper">
											<input type="hidden" name="plannerNo" value="${planner.plannerNo}">
											<button type="button" class="btn btn-sm main-btn">바로가기</button>
											<c:if test="${planner.plannerPermission eq '3'}">
												<button type="button" class="btn  btn-sm del-btn planner-delete">삭제</button>
											</c:if>
										</div>
										<div>
											<i class="fas fa-eye"></i>&nbsp;${planner.plannerCount}
										</div>
									</div>
								</div>
							</div>
						</div>
					</c:forEach>
				</c:if>

			</div>

			<!-- 페이징바 -->
			<div class="pagination-wrapper" id="cPlannerPaging">
				<nav aria-label="Page navigation">
					<ul class="pagination justify-content-center"></ul>
				</nav>
			</div>
			
			<script>
				var cPlannerSize = ${cPlannerList.size()};
				var cCurrentPage = 1;
			</script>
		</div>
	</div>

	<jsp:include page="../common/footer.jsp" />

	<script src="https://code.jquery.com/jquery-3.4.1.min.js"
		integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
		integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
		crossorigin="anonymous"></script>
	<script
		src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
		integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
		crossorigin="anonymous"></script>

	<script>
        $(function () {
        	// 수정중인 플래너 페이징바 초기화
        	pagingHtmlFn(uPlannerSize, uCurrentPage, 'u');
        	disCurrent($("#uPlannerPaging").children().children().children(), uCurrentPage);
        		
        	// 완료된 플래너 페이징바 초기화
        	pagingHtmlFn(cPlannerSize, cCurrentPage, 'c');
        	disCurrent($("#cPlannerPaging").children().children().children(), uCurrentPage);
        	

            $(".planner-delete").on("click", function () {
                var plannerNo = $(this).parent().children("input").val();

                if (confirm("플래너를 삭제하시겠습니까?")) {
                    location.href="delPlanner?plannerNo=" + plannerNo;
                }
            });
        });
		
		// 페이징 번호 작성 함수
        function pagingHtmlFn(plannerSize, currentPage, ch){
			
			var maxPage = (plannerSize + (limit - (plannerSize % limit)) % limit) / limit;
            
            var totalPage = Math.ceil(plannerSize/limit);    // 총 페이지 수
            var pageGroup = Math.ceil(currentPage/pagingBarSize);    // 페이지 그룹
            
            var last = pageGroup * pagingBarSize;    // 화면에 보여질 마지막 페이지 번호
            
            if(last > totalPage){
                last = totalPage;
            }
            var first = ((pageGroup - 1) * pageGroup) + 1;    // 화면에 보여질 첫번째 페이지 번호
            /* var next = last+1;
            var prev = first-1; */
            
        	var $plannerChild = $("#" + ch + "PlannerPaging").children().children();
			
        	var pagingHtml = "";
        	if(currentPage > 1){
        		pagingHtml += "<li class='page-item'><span class='page-link page-num " + ch + "Paging'><<</span></li>";
        		pagingHtml += "<li class='page-item'><span class='page-link page-num " + ch + "Paging'><</span></li>";
        	}
        	
        	for(var i=first; i<=last; i++){
        		pagingHtml += "<li class='page-item'><span class='page-link page-num " + ch + "Paging'>" + i + "</span></li>";
        	}
        	
        	if(currentPage < maxPage){
        		pagingHtml += "<li class='page-item'><span class='page-link page-num " + ch + "Paging'>></span></li>";
        		pagingHtml += "<li class='page-item'><span class='page-link page-num " + ch + "Paging'>>></span></li>";
        	}
        	
        	// 플래너 display:none으로 초기화
			for(var i=1; i<=plannerSize; i++){
				$("#" + ch + "Planner" + i).addClass("displayNone");
			}
			
			var startIndex = (currentPage - 1) * limit + 1;
    		var endIndex = startIndex + limit - 1;
    		if(endIndex > plannerSize){
    			endIndex = plannerSize;
    		}
			// 해당되는 플래너들만 보여줌
			for(var i=startIndex; i<=endIndex; i++){
				$("#" + ch + "Planner" + i).removeClass("displayNone");
			}
        	
        	$plannerChild.html(pagingHtml);
        	
        	$("." + ch + "Paging").on("click", function(){
        		var $click = $(this);
        		
        		if($click.text() == "<<"){
        			window[ch + "CurrentPage"] = 1;
        		}else if($click.text() == "<"){
        			window[ch + "CurrentPage"] = Number(window[ch + "CurrentPage"]) - 1;
        		}else if($click.text() == ">"){
        			window[ch + "CurrentPage"] = Number(window[ch + "CurrentPage"]) + 1;
        		}else if($click.text() == ">>"){
        			window[ch + "CurrentPage"] = totalPage;
        		}else if($click.text() != window[ch + "CurrentPage"]){
       				window[ch + "CurrentPage"] = $click.text();
        		}
        		
        		$click = $click.parent().parent();
       			pagingHtmlFn(plannerSize, window[ch + "CurrentPage"], ch);
       			disCurrent($click.children(), window[ch + "CurrentPage"]);
        	});
        	
        }
        
        // 현재 페이지번호와 같을 시 disabled시키는 함수
        function disCurrent(list, currentPage){
        	$(list).each(function(index, item){
        		if($(item).text() == currentPage){
        			$(item).addClass("disabled");
        		}else{
        			$(item).removeClass("disabled");
        		}
        	});
        }
    </script>
</body>
</html>