import React, { Component } from 'react';
import axios from 'axios';
import { Link } from 'react-router-dom';

class Show extends Component {

    constructor(props) {
        super(props);
        this.state = {
            project: {}
        };
    }

    componentDidMount() {
        axios.get('http://localhost:9090/project/'+this.props.match.params.id)
            .then(res => {
                this.setState({ project: res.data });
                console.log(this.state.project);
            });
    }

    delete(id){
        console.log(id);
        axios.delete('http://localhost:9090/project/'+id)
            .then((result) => {
                this.props.history.push("/")
            });
    }

    render() {
        return (
            <div className="container">
                <div className="panel panel-default">
                    <div className="panel-heading">
                        <h3 className="panel-title">
                            Project Details
                        </h3>
                    </div>
                    <div className="panel-body">
                        <h4><Link to="/"><span className="glyphicon glyphicon-th-list" aria-hidden="true"></span> Project List</Link></h4>
                        <dl>
                            <dt>Name:</dt>
                            <dd>{this.state.project.name}</dd>
                            <dt>Description:</dt>
                            <dd>{this.state.project.description}</dd>
                            <dt>State:</dt>
                            <dd>{this.state.project.state}</dd>
                            {/*<dt>Postal Code:</dt>*/}
                            {/*<dd>{this.state.customer.postalCode}</dd>*/}
                            {/*<dt>Phone Number:</dt>*/}
                            {/*<dd>{this.state.customer.phone}</dd>*/}
                        </dl>
                        <Link to={`/edit/${this.state.project.id}`} className="btn btn-success">Edit</Link>&nbsp;
                        <button onClick={this.delete.bind(this, this.state.project.id)} className="btn btn-danger">Delete</button>
                    </div>
                </div>
            </div>
        );
    }
}

export default Show;