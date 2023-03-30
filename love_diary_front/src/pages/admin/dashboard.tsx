import {PropsWithChildren, RefObject, useEffect, useRef} from "react";
import axios from "axios";
import Map from "@/pages/component/map";

function Dashboard(props:PropsWithChildren) {
    const sidebarRef: RefObject<HTMLElement> = useRef(null);
    const maxSidebarRef: RefObject<HTMLElement> = useRef(null);
    const miniSidebarRef: RefObject<HTMLElement> = useRef(null);
    const roundoutRef: RefObject<HTMLElement> = useRef(null);
    const maxToolbarRef: RefObject<HTMLElement> = useRef(null);
    const logoRef: RefObject<HTMLElement> = useRef(null);
    const contentRef: RefObject<HTMLElement> = useRef(null);
    const moonRef: RefObject<HTMLElement> = useRef(null);
    const sunRef: RefObject<HTMLElement> = useRef(null);
    useEffect(() => {
        sidebarRef.current = document.querySelector("aside");
        maxSidebarRef.current = document.querySelector(".max");
        miniSidebarRef.current = document.querySelector(".mini");
        roundoutRef.current = document.querySelector(".roundout");
        maxToolbarRef.current = document.querySelector(".max-toolbar");
        logoRef.current = document.querySelector('.logo');
        contentRef.current = document.querySelector('.content');
        moonRef.current = document.querySelector(".moon");
        sunRef.current = document.querySelector(".sun");
    }, []);

    function setDark(val: string) {
        if (val === "dark") {
            document.documentElement.classList.add('dark');
            moonRef.current?.classList.add("hidden");
            sunRef.current?.classList.remove("hidden");
        } else {
            document.documentElement.classList.remove('dark');
            sunRef.current?.classList.add("hidden");
            moonRef.current?.classList.remove("hidden");
        }
    }

    // useEffect(() => {
    //     // axios
    //     //     .get('http://localhost:8080/api/v1/auth/student/1')
    //     //     .then((res) => {
    //     //         console.log(res.data)
    //     //
    //     //     }).catch(() => {
    //     // });
    //     axios
    //         .get('http://localhost:8080/api/v1/auth/student/export')
    //         .then((res) => {
    //             console.log(res.data)
    //
    //         }).catch(() => {
    //     });
    // }, []);
    const download = () => {
        axios
            ({
                url :'http://localhost:8080/api/v1/auth/student/export',
                method : 'GET',
                responseType: 'blob'
            })
            .then((response ) => {
                const url = window.URL.createObjectURL(new Blob([response.data]));
                const link = document.createElement('a');
                link.href = url;
                link.setAttribute('download', 'student.csv');
                document.body.appendChild(link);
                link.click();
                link.parentNode.removeChild(link);
            }).catch(() => {
        });
    }

    function openNav() {
        if (sidebarRef.current?.classList.contains('-translate-x-48')) {
            // max sidebar
            sidebarRef.current?.classList.remove("-translate-x-48");
            sidebarRef.current?.classList.add("translate-x-none");
            maxSidebarRef.current?.classList.remove("hidden");
            maxSidebarRef.current?.classList.add("flex");
            miniSidebarRef.current?.classList.remove("flex");
            miniSidebarRef.current?.classList.add("hidden");
            maxToolbarRef.current?.classList.add("translate-x-0");
            maxToolbarRef.current?.classList.remove("translate-x-24", "scale-x-0");
            logoRef.current?.classList.remove("ml-12");
            contentRef.current?.classList.remove("ml-12");
            contentRef.current?.classList.add("ml-12", "md:ml-60");
        } else {
            // mini sidebar
            sidebarRef.current?.classList.add("-translate-x-48");
            sidebarRef.current?.classList.remove("translate-x-none");
            maxSidebarRef.current?.classList.add("hidden");
            maxSidebarRef.current?.classList.remove("flex");
            miniSidebarRef.current?.classList.add("flex");
            miniSidebarRef.current?.classList.remove("hidden");
            maxToolbarRef.current?.classList.add("translate-x-24", "scale-x-0");
            maxToolbarRef.current?.classList.remove("translate-x-0");
            logoRef.current?.classList.add('ml-12');
            contentRef.current?.classList.remove("ml-12", "md:ml-60");
            contentRef.current?.classList.add("ml-12");
        }
    }

    return (
        <>
            <div className="body bg-white dark:bg-[#0F172A]">
                <div
                    className="fixed w-full z-30 flex bg-white dark:bg-[#0F172A] p-2 items-center justify-center h-16 px-10">
                    <div
                        className="logo ml-12 dark:text-white  transform ease-in-out duration-500 flex-none h-full flex items-center justify-center">
                        NERVE
                    </div>
                    {/*SPACER*/}
                    <div className="grow h-full flex items-center justify-center"></div>
                    <div className="flex-none h-full text-center flex items-center justify-center">
                        <div className="flex space-x-3 items-center px-3">
                            <div className="flex-none flex justify-center">
                                <div className="w-8 h-8 flex ">
                                    <img
                                        src="https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcShta_GXR2xdnsxSzj_GTcJHcNykjVKrCBrZ9qouUl0usuJWG2Rpr_PbTDu3sA9auNUH64&usqp=CAU"
                                        alt="profile" className="shadow rounded-full object-cover"/>
                                </div>
                            </div>
                            <div className="hidden md:block text-sm md:text-md text-black dark:text-white">Charlie Tuấn
                            </div>
                        </div>

                    </div>
                </div>
                <aside
                    className="w-60 -translate-x-48 fixed transition transform ease-in-out duration-1000 z-50 flex h-screen bg-[#1E293B] ">
                    {/*open sidebar button */}
                    <div
                        className="max-toolbar translate-x-24 scale-x-0 w-full -right-6 transition transform ease-in duration-300 flex items-center justify-between border-4 border-white dark:border-[#0F172A] bg-[#1E293B]  absolute top-2 rounded-full h-12">

                        <div className="flex pl-4 items-center space-x-2 ">
                            <div>
                                <div onClick={() => setDark('dark')}
                                     className="moon text-white hover:text-blue-500 dark:hover:text-[#38BDF8]">
                                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24"
                                         strokeWidth={3} stroke="currentColor" className="w-4 h-4">
                                        <path strokeLinecap="round" strokeLinejoin="round"
                                              d="M21.752 15.002A9.718 9.718 0 0118 15.75c-5.385 0-9.75-4.365-9.75-9.75 0-1.33.266-2.597.748-3.752A9.753 9.753 0 003 11.25C3 16.635 7.365 21 12.75 21a9.753 9.753 0 009.002-5.998z"/>
                                    </svg>
                                </div>
                                <div onClick={() => setDark('light')}
                                     className="sun hidden text-white hover:text-blue-500 dark:hover:text-[#38BDF8]">
                                    <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24"
                                         stroke-width="1.5" stroke="currentColor" className="w-4 h-4">
                                        <path stroke-linecap="round" stroke-linejoin="round"
                                              d="M12 3v2.25m6.364.386l-1.591 1.591M21 12h-2.25m-.386 6.364l-1.591-1.591M12 18.75V21m-4.773-4.227l-1.591 1.591M5.25 12H3m4.227-4.773L5.636 5.636M15.75 12a3.75 3.75 0 11-7.5 0 3.75 3.75 0 017.5 0z"/>
                                    </svg>
                                </div>
                            </div>
                            <div className="text-white hover:text-blue-500 dark:hover:text-[#38BDF8]">
                                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={3}
                                     stroke="currentColor" className="w-4 h-4">
                                    <path strokeLinecap="round" strokeLinejoin="round"
                                          d="M14.857 17.082a23.848 23.848 0 005.454-1.31A8.967 8.967 0 0118 9.75v-.7V9A6 6 0 006 9v.75a8.967 8.967 0 01-2.312 6.022c1.733.64 3.56 1.085 5.455 1.31m5.714 0a24.255 24.255 0 01-5.714 0m5.714 0a3 3 0 11-5.714 0"/>
                                </svg>
                            </div>
                        </div>
                        <div
                            className="flex items-center space-x-3 group bg-gradient-to-r dark:from-cyan-500 dark:to-blue-500 from-indigo-500 via-purple-500 to-purple-500  pl-10 pr-2 py-1 rounded-full text-white  ">
                            <div className="transform ease-in-out duration-300 mr-12">
                                NERVE
                            </div>
                        </div>
                    </div>
                    <div onClick={openNav}
                         className="-right-6 transition transform ease-in-out duration-500 flex border-4 border-white dark:border-[#0F172A] bg-[#1E293B] dark:hover:bg-blue-500 hover:bg-purple-500 absolute top-2 p-3 rounded-full text-white hover:rotate-45">
                        <img src="/img/heart-attack.png" alt=""/>

                    </div>
                    {/*<!-- MAX SIDEBAR*/}
                    <div className="max hidden text-white mt-20 flex-col space-y-2 w-full h-[calc(100vh)]">
                        <div
                            className="hover:ml-4 w-full text-white hover:text-purple-500 dark:hover:text-blue-500 bg-[#1E293B] p-2 pl-8 rounded-full transform ease-in-out duration-300 flex flex-row items-center space-x-3">
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5}
                                 stroke="currentColor" className="w-4 h-4">
                                <path strokeLinecap="round" strokeLinejoin="round"
                                      d="M2.25 12l8.954-8.955c.44-.439 1.152-.439 1.591 0L21.75 12M4.5 9.75v10.125c0 .621.504 1.125 1.125 1.125H9.75v-4.875c0-.621.504-1.125 1.125-1.125h2.25c.621 0 1.125.504 1.125 1.125V21h4.125c.621 0 1.125-.504 1.125-1.125V9.75M8.25 21h8.25"/>
                            </svg>
                            <button>
                                home
                            </button>
                        </div>
                        <div
                            className="hover:ml-4 w-full text-white hover:text-purple-500 dark:hover:text-blue-500 bg-[#1E293B] p-2 pl-8 rounded-full transform ease-in-out duration-300 flex flex-row items-center space-x-3">
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
                                 stroke="currentColor" className="w-4 h-4">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      d="M3.75 5.25h16.5m-16.5 4.5h16.5m-16.5 4.5h16.5m-16.5 4.5h16.5"/>
                            </svg>
                            <button>
                                Table
                            </button>
                        </div>
                        <div
                            className="hover:ml-4 w-full text-white hover:text-purple-500 dark:hover:text-blue-500 bg-[#1E293B] p-2 pl-8 rounded-full transform ease-in-out duration-300 flex flex-row items-center space-x-3">
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
                                 stroke="currentColor" className="w-4 h-4">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      d="M10.5 6a7.5 7.5 0 107.5 7.5h-7.5V6z"/>
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      d="M13.5 10.5H21A7.5 7.5 0 0013.5 3v7.5z"/>
                            </svg>
                            <button>
                                Graph
                            </button>
                        </div>
                    </div>
                    {/*<!-- MINI SIDEBAR*/}
                    <div className="mini mt-20 flex flex-col space-y-2 w-full h-[calc(100vh)]">
                        <div
                            className="hover:ml-4 justify-end pr-5 text-white hover:text-purple-500 dark:hover:text-blue-500 w-full bg-[#1E293B] p-3 rounded-full transform ease-in-out duration-300 flex">
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" strokeWidth={1.5}
                                 stroke="currentColor" className="w-4 h-4">
                                <path strokeLinecap="round" strokeLinejoin="round"
                                      d="M2.25 12l8.954-8.955c.44-.439 1.152-.439 1.591 0L21.75 12M4.5 9.75v10.125c0 .621.504 1.125 1.125 1.125H9.75v-4.875c0-.621.504-1.125 1.125-1.125h2.25c.621 0 1.125.504 1.125 1.125V21h4.125c.621 0 1.125-.504 1.125-1.125V9.75M8.25 21h8.25"/>
                            </svg>
                        </div>
                        <div
                            className="hover:ml-4 justify-end pr-5 text-white hover:text-purple-500 dark:hover:text-blue-500 w-full bg-[#1E293B] p-3 rounded-full transform ease-in-out duration-300 flex">
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
                                 stroke="currentColor" className="w-4 h-4">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      d="M3.75 5.25h16.5m-16.5 4.5h16.5m-16.5 4.5h16.5m-16.5 4.5h16.5"/>
                            </svg>
                        </div>
                        <div
                            className="hover:ml-4 justify-end pr-5 text-white hover:text-purple-500 dark:hover:text-blue-500 w-full bg-[#1E293B] p-3 rounded-full transform ease-in-out duration-300 flex">
                            <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5"
                                 stroke="currentColor" className="w-4 h-4">
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      d="M10.5 6a7.5 7.5 0 107.5 7.5h-7.5V6z"/>
                                <path stroke-linecap="round" stroke-linejoin="round"
                                      d="M13.5 10.5H21A7.5 7.5 0 0013.5 3v7.5z"/>
                            </svg>
                        </div>
                    </div>

                </aside>
                {/*CONTENT -->*/}
                <div className="content ml-12 transform ease-in-out duration-500 pt-20 px-2 md:px-5 pb-4 ">
                    <div className="flex px-5 py-3 text-gray-700  rounded-lg bg-gray-50 dark:bg-[#1E293B] "
                         aria-label="Breadcrumb">
                        {props.children}
                        <Map />
                    </div>
                    <div>
                        {/*<button onClick={download}>Download CSV</button>*/}
                        <div className="top-10 left-10 relative">ta cung nhau</div>
                        <div>ngh iden dau dau</div>
                        <div>xa nhau</div>
                        <div>thuong nhieu</div>
                    </div>
                </div>
            </div>
        </>
    )
}

export default Dashboard;
