import HomeCoordinator from "./coordinator/homeCoordinator.jsx";
import {Link} from "react-router-dom";
import HomeCompany from "./company/homeCompany.jsx";
import StudentHome from "./student/studentHome.jsx";
import HomeTeacher from "./teacher/homeTeacher.jsx";

function Home() {
    const authority = localStorage.getItem('authority');
    return(
        <>

            {authority === "COORDINATOR" ? <HomeCoordinator/> :
                authority === "COMPANY" ? <HomeCompany/> :
                authority === "STUDENT" ? <StudentHome/> :
                authority === "TEACHER" ? <HomeTeacher/> :
                    <><h1>Home page!</h1></>}


        </>
    )
}

export default Home