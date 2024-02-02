
## 목차
1. [프로젝트 개요](#프로젝트-개요)
2. [요구사항](#요구사항)
3. [추가로 구현된 사항](#추가로-구현된-사항)
4. [개발 환경 및 기술 스택](#개발-환경-및-기술-스택)
5. [기능 설명](#기능-설명)

## 프로젝트 개요
어떤 파일들은 첨부시 보안에 문제가 될 수 있습니다. 특히 exe, sh 등의 실행파일이 존재할 경우 서버에 올려서 실행이 될 수 있는 위험이 있어 파일 확장자 차단을 하게 되었습니다.

## 요구사항
1. 고정 확장자는 차단을 자주하는 확장자를 리스트이며, default는 unCheck. <br>
2. 고정 확장자는 check or uncheck를 할 경우 db에 저장. - 새로고침시 유지.<br>
3. 확장자 최대 입력 길이는 20자리.<br>
4. 추가버튼 클릭시 db 저장되며, 아래쪽 영역에 표현.<br>
5. 커스텀 확장자는 최대 200개까지 추가가 가능.<br>
6. 확장자 옆 X를 클릭시 db에서 삭제.<br>

## 추가로 구현된 사항
1. 파일 업로드가 가능한 index 페이지 구현. 업로드 되는 데이터는 실제 파일이 아닌 파일명만 저장. 파일 선택을 하지 않고 업로드 버튼 클릭 시 오류 발생.
2. 확장자의 차단 여부에 따라 업로드 수행.
3. 중복된 업로드 파일 검증에 따른 수행.
4. 커스텀 확장자 추가 중복 검증 로직.
5. 고정 확장자, 커스텀 확장자에서 업로드 된 파일의 확장자를 차단 요청 시 파일 삭제 후 차단.

## 개발 환경 및 기술 스택
### Front-end
| <img src="https://profilinator.rishav.dev/skills-assets/html5-original-wordmark.svg" alt="HTML5" width="50px" height="50px" /> | <img src="https://profilinator.rishav.dev/skills-assets/css3-original-wordmark.svg" alt="CSS3" width="50px" height="50px" /> | <img src="https://profilinator.rishav.dev/skills-assets/javascript-original.svg" alt="JavaScript" width="50px" height="50px" /> | <img src="https://www.vectorlogo.zone/logos/jquery/jquery-vertical.svg" alt="React.js" width="50px" height="50px" /> |
| :---: | :---: | :---: |:--------------------------------------------------------------------------------------------------------------------:|
| HTML5 | CSS | JavaScripts |                                                        jQuery                                                        |

### Back-end
| <img src="https://profilinator.rishav.dev/skills-assets/java-original-wordmark.svg" alt="Java" width="50px" height="50px" /> | <img src="https://www.seekpng.com/png/full/142-1425436_spring-boot.png" alt="Spring-Boot" width="110px" height="50px" /> | <img src="https://minkukjo.github.io/assets//img/spring-data-logo.png" alt="jwt" width="50px" height="50px" /> | <img src="https://profilinator.rishav.dev/skills-assets/mysql-original-wordmark.svg" alt="SpringSecurity" width="50px" height="50px"> |
| :---: | :---: |:---------------------------------------------------------------------------------------------------------------------:|:----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------:|
| Java | Spring-Boot |                                                    Spring Data JPA                                                    |                                                                                                         Mysql                                                                                                          |                                         |

### Deploy

| <img src="https://seeklogo.com/images/A/aws-ec2-elastic-compute-cloud-logo-2F9E73DBA5-seeklogo.com.png" alt="EC2" height="50px" width="50px" /> | <img src="https://profilinator.rishav.dev/skills-assets/docker-original-wordmark.svg" alt="docker" width="50px" height="50px" /> | <img src="https://cdn.worldvectorlogo.com/logos/aws-rds.svg" alt="NGiNX" width="50px" height="50px" /> |
| :---: | :---: |:------------------------------------------------------------------------------------------------------:|
| EC2 | Docker |                                                  RDS                                                   |

|<img src="https://profilinator.rishav.dev/skills-assets/git-scm-icon.svg" alt="Git" height="50px" width="50px" />  | <img src="https://profilinator.rishav.dev/skills-assets/linux-original.svg" alt="Linux" height="50px" width="50px" /> |
| :---: | :---: |
| Git  | Linux |

## 기능 설명

|      기능명      |                         기능 설명                         |
|:-------------:|:-----------------------------------------------------:|
|     업로드 전     |         파일을 선택하지 않고 업로드 버튼을 누를 시에 알림창을 띄웁니다.          |
|  파일 업로드 및 삭제  |                 파일을 업로드하고 삭제가 가능합니다.                  |
|   고정 확장자 차단   |        고정적으로 나열된 확장자들을 체크박스를 통해 차단/해제가 가능합니다.         |
|  커스텀 확장자 차단   |          사용자가 차단할 확장자를 입력하여 차단하고 해제가 가능합니다.           |
| 업로드된 파일 - 고정  | 이미 업로드된 파일의 고정 확장자를 차단할 시에 파일을 삭제하고 차단할지 선택할 수 있습니다.  |
| 업로드된 파일 - 커스텀 | 이미 업로드된 파일의 커스텀 확장자를 차단할 시에 파일을 삭제하고 차단할지 선택할 수 있습니다. |
|  중복 검증 - 커스텀  |       이미 차단된 커스텀 확장자를 다시 추가하려 할 시에 차단이 불가능합니다.        |
| 확장자 길이 최대 20  |            20자리가 넘는 확장자를 입력할 시에 요청이 거절됩니다.            |

|                                                                 업로드 전                                                                 |
|                                                                 :---:                                                                 |
| <image src = "https://github.com/gitKDH/baekjoon/assets/119571075/a16651b5-99e7-4f1a-b9f9-5ddcffb4ec16" width="800px" height="500px"> |
|                                                              파일 업로드 및 삭제                                                              |
| <image src = "https://github.com/gitKDH/baekjoon/assets/119571075/3a902864-c843-4e16-bf11-55fb8eddc7ab" width="800px" height="500px"> |
|                                                               고정 확장자 차단                                                               |
|                               <image src = "https://github.com/gitKDH/baekjoon/assets/119571075/e1c73e93-f5f9-46d4-9803-68997ddf66bb" width="800px" height="500px">                               |
|                                                              커스텀 확장자 차단                                                               |
|                               <image src = "https://github.com/gitKDH/baekjoon/assets/119571075/d292b7b1-2f59-491c-b710-8cfb7a39d509" width="800px" height="500px">                               |
|                                                             업로드된 파일 - 고정                                                              |
|                               <image src = "https://github.com/gitKDH/baekjoon/assets/119571075/06daa46b-d6ac-4832-8217-9ff0fcc12b3a" width="800px" height="500px">                               |
|                                                             업로드된 파일 - 커스텀                                                             |
|                               <image src = "https://github.com/gitKDH/baekjoon/assets/119571075/a3d86eae-b0d4-4714-a97f-c817ffc61759" width="800px" height="500px">                               |
|                                                              중복 검증 - 커스텀                                                              |
|                               <image src = "https://github.com/gitKDH/baekjoon/assets/119571075/7a6f81db-6d79-4be7-be21-8d622b39d0ca" width="800px" height="500px">                               |
|                                                             확장자 길이 최대 20                                                              |
|                               <image src = "https://github.com/gitKDH/baekjoon/assets/119571075/e6f4b5d8-409b-4610-97c5-27a08697a93a" width="800px" height="500px">                               |