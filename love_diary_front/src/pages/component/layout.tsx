import { PropsWithChildren } from "react";

export function Layout(props:PropsWithChildren){
    return(
        <>
            <header>
                hedaer
            </header>
            <main>
                {props.children}
            </main>
            <footer>footer</footer>
        </>
    )
}