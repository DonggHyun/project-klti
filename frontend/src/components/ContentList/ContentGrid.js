import Grid from "@toast-ui/react-grid";
import React from 'react'


const ContentGrid = React.forwardRef((props, ref) => {
/*    if (props.highlight === true) {
        tableOptions.cell.focused = { background: "none", border: "none" };
        tableOptions.row = {
            background: "white",
            hover: {
                background: "#a2bbeb !important"
            }
        };
    } else {
        tableOptions.cell.focused = { background: "none", border: "#0f75bc" };
        tableOptions.row = {
            odd: {
                background: "white"
            },
            even: {
                background: "#dcddde"
            }
        };
    }*/

    //TuiGrid.applyTheme(null, tableOptions);
    return (
        <>
            <Grid
                ref={ref}
                rowHeight={25}
                bodyHeight={100}
                {...props}
            />
        </>
    );
});

export default ContentGrid;