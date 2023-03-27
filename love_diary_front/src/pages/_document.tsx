import { Html, Head, Main, NextScript } from 'next/document'

interface Color{
    color: string;
}

export default function Document() {
  return (
    <Html lang="en">
      <Head />
      <body>
        <Main />
        <NextScript />
      </body>
    </Html>
  )
}
