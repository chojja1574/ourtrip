<!DOCTYPE html>
<html lang = "ko">
    <html lang = "ko">
    <head>
    <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css" 
        integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.4.1.min.js"  
        integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="  crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" 
        integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js" 
        integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6" crossorigin="anonymous"></script>
        <!-- style -->
        <link rel="stylesheet" type="text/css" href="slick/slick.css"/>
        
        <!-- 호환성 -->
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

        <!-- javascript lib -->
        <script type="text/javascript" src="slick/slick.min.js"></script>

        <!-- 폰트 -->
        <link href="https://fonts.googleapis.com/css?family=Nanum+Pen+Script|Ubuntu&display=swap" rel="stylesheet">

        <!-- 공용 css -->
        <link rel="stylesheet" href="css/common.css">

        <title> 배포 플래너 </title>
        <style>
            /* div{
                border:1px dotted black;
            } */
            div:focus {
                outline: none;
            }

            /* 슬라이더 크기 */
            /* 높이설정 */
            .slick-slider{
                height:800px;
            }
            /* 넓이 설정 */
            .slider-wrapper{
                width:420px;
            }

            /* 슬라이더 내용크기 */
            .slick-list{
                height:100%;
            }

            /* 내용크기 + 드래그범위 */
            .slick-track{
                height:100%!important;
            }
            

            .slick-slide{
                border:0px;
            }
            
            @media screen and (max-width: 414px) {
                .container{
                    padding:0px;
                }

                .slick-slider{
                    width:100%;
                    height:100%;
                }

                .slider-wrapper{
                    width:100%;
                    height:100%;
                }


                .header{
                    display: none;
                }
                /* 할거 아래 푸터지우기 */
                .card-footer{
                    display: none!important;
                }
            } 

            .p-cont{
            text-align: left;
            display: block;
            }

            body{
                font-family: 'Ubuntu', sans-serif;
                font-family: 'Nanum Pen Script', cursive;
                font-size: 24px;
            }

        </style>
    </head>
    <body>
        <div class="header">
            <!--start header area-->
            <div class="container-fluid px-0" style="background-color: white;">
                <div class="container my-0 px-0">
                    <div class="d-flex">
                        <a href="main.html">
                            <img class="logo" id="mainlogo" src="images/ourtrip_logo2.png" alt="이미지"
                                style="width: 220px; height: auto;">
                        </a>
                        <div class="btn-wrapper ml-auto mt-4">
                            <a href="#" class="btn admin-btn-rev" type="button">로그아웃</a>
                        </div>
                    </div>
                </div>
            </div>
            <!--end header area-->
            <!-- start nav area -->
            <nav class="navbar navbar-expand-sm nabigater" style="background-color: white; text-align: center;">
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                    <i class="fas fa-bars"></i>
                </button>
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <div class="container my-0 px-0">
                        <div class="col-sm-3 nav-item">
                            <a href="#" class="nav-text">
                                <h5>대시보드</h5>
                            </a>
                        </div>
                        <div class="col-sm-3 nav-item">
                            <a href="#" class="nav-text">
                                <h5>회원목록</h5>
                            </a>
                        </div>
                        <div class="col-sm-3 nav-item">
                            <a href="#" class="nav-text">
                                <h5>플래너목록</h5>
                            </a>
                        </div>
                        <div class="col-sm-3 nav-item">
                            <a href="#" class="nav-text">
                                <h5>공지</h5>
                            </a>
                        </div>
                    </div>
                </div>
            </nav>
            <!--end nav area-->
        </div>
        <div class="container d-flex">
            <!-- 이미지 슬라이드 -->
            <div class="slider-wrapper mx-auto">
                <div class="slider">
                    <!-- 1-day 시작 -->
                    <div class="card">
                        <h2 class="card-header main-back" style="height:8%;">떠나요 제주도(경비:10만원)</h2>
                        <div class="card-body" style="overflow: auto; height: 84%;">
                            <h1 style="color:#18a8f1;">Day-1</h1>
                            <div class="btn p-cont" 
                            data-toggle="modal" data-target="#myModal">
                                <h1 class="font-weight-bold">08:00</h1>
                                <h2>서울역</h2>
                                <h3>경비 : 10,000원 </h3>
                            </div>
                            <div class="btn p-cont"
                            data-toggle="modal" data-target="#myModal">
                                <h1 class="font-weight-bold">10:00</h1>
                                <h2>동대문</h2>
                                <h3>경비 : 10,000원 </h3>
                            </div>
                            <div class="btn p-cont"
                            data-toggle="modal" data-target="#myModal">
                                <h1 class="font-weight-bold">14:00</h1>
                                <h2>을지로</h2>
                                <h3>경비 : 10,000원 </h3>
                            </div>
                            <div class="btn p-cont"
                            data-toggle="modal" data-target="#myModal">
                                <h1 class="font-weight-bold">16:00</h1>
                                <h2>서울역</h2>
                                <h3>경비 : 10,000원 </h3>
                            </div>
            
                            <div class="btn p-cont"
                            data-toggle="modal" data-target="#myModal">
                                <h1 class="font-weight-bold">20:00</h1>
                                <h2>술집</h2>
                                <h3>경비 : 10,000원 </h3>
                            </div>
                            <div style="width:100%; height: 350px; background-color: yellow;"></div>
                        </div>
                        <div class="card-footer text-muted d-flex hideCon" style="height:8%;">
                            <a href="#" class="btn main-btn mr-auto">복사</a>
                            <a href="#" class="btn gray-btn ml-auto">목록으로</a>
                        </div>
                    </div>
                    
                    <!-- 2-day 시작 -->
                    <div class="card">
                        <h2 class="card-header main-back" style="height:8%;">떠나요 제주도(경비:40만원)</h2>
                        <div class="card-body" style="overflow: auto; height: 84%;">
                            <h1 style="color:#18a8f1;">Day-1</h1>
                            <div class="btn p-cont" 
                            data-toggle="modal" data-target="#myModal">
                                <h1 class="font-weight-bold">08:00</h1>
                                <h2>부산역</h2>
                                <h3>경비 : 10,000원 </h3>
                            </div>
                            <div class="btn p-cont"
                            data-toggle="modal" data-target="#myModal">
                                <h1 class="font-weight-bold">10:00</h1>
                                <h2>한국</h2>
                                <h3>경비 : 10,000원 </h3>
                            </div>
                            <div class="btn p-cont"
                            data-toggle="modal" data-target="#myModal">
                                <h1 class="font-weight-bold">14:00</h1>
                                <h2>중국</h2>
                                <h3>경비 : 10,000원 </h3>
                            </div>
                            <div class="btn p-cont"
                            data-toggle="modal" data-target="#myModal">
                                <h1 class="font-weight-bold">16:00</h1>
                                <h2>상하이</h2>
                                <h3>경비 : 10,000원 </h3>
                            </div>
                            <div style="width:100%; height: 350px; background-color: yellow;"></div>
                        </div>
                        <div class="card-footer text-muted d-flex hideCon" style="height:8%;">
                            <a href="#" class="btn main-btn mr-auto">복사</a>
                            <a href="#" class="btn gray-btn ml-auto">목록으로</a>
                        </div>
                    </div>
                    <!-- 2틀끝 -->
                    </div>
                </div>
            </div>
            <!-- slider wrapper 끝 -->
        </div>

        <!-- 모달창 -->
        <div class="modal" id="myModal">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header main-back">
                        <h5 class="modal-title font-weight-bold">DAY - 1</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <h4>08:00</h4>
                        <h5>서울역</h5>
                        <h6>경비 : 10,000원 </h6>
                        <p>메모</p>
                        <textarea readonly style="height:200px; width: 100%; resize: none; padding: 20px;" >서울역에서 3번출구에 8시까지 모인다
빨리안오면 벌금20000만원을 내야한다
아침은해결하고오기바란다
                        </textarea>
                        <p>위치</p>
                            <img style="height: 200px; width: 100%; display: block;
                                        padding:20px;"
                                src="images/ourtrip_logo.png"
                                alt="sub-map">    
                        
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" 
                        data-dismiss="modal">되돌아가기</button>
                    </div>
                </div>
            </div>
        </div>
    </body>

    <script>
        // 슬라이더
        $(".slider").slick({
            // 아래 누르는 버튼
            dots: false,
            autoplay : false,
            autoplaySpeed: 3000,
            arrows: false,
            responsive : [
                {
                    breakpoint: 768,
                    settings : {
                        arrows: false,
                        dots : false
                    }
                }
            ]
        });
    </script>
    
</html>