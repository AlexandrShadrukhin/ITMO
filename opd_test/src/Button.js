import React from 'https://cdn.skypack.dev/react'

const Button = ({ as, children, filled, secondary, ...rest }) => {
    const that = {
        as
    }
    return (
        <that.as className={`dir-control ${secondary ? 'dir-control--secondary' : ''} ${filled ? 'dir-control--filled' : ''}`} {...rest} >
            {children}
            <span/>
            <span/>
            <span/>
            <span/>
            <b aria-hidden="true">{children}</b>
            <b aria-hidden="true">{children}</b>
            <b aria-hidden="true">{children}</b>
            <b aria-hidden="true">{children}</b>
        </that.as>
    )
}
Button.defaultProps = {
    as: 'button'
}


export default Button;